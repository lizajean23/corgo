package com.example.corgo1.appFragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.corgo1.MainActivity
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentVaccineBinding
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class VaccineFragment:Fragment(R.layout.fragment_vaccine) {


    private var _binding: FragmentVaccineBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVaccineBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val vaccinePref = preferences.getString("vaccine", "")
        val datePref = preferences.getString("date", "")
        val builder = AlertDialog.Builder(requireContext())
        val myCalendar =  Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        binding.vaccinetext.text = vaccinePref
        binding.datetext.text = datePref

        binding.save.setOnClickListener {


            val vaccineName = binding.vaccinename.text.toString()
            val vaccineDate = binding.vaccinedate.text.toString()

            if(vaccineName.isEmpty() || vaccineDate.isEmpty()){
                return@setOnClickListener
            }
            val vaccines = binding.vaccinetext.text.toString()
            val vaccinesUpdated = vaccines + '\n' + '\n'+ vaccineName

            val dates = binding.datetext.text.toString()
            val datesUpdated = dates + '\n' + '\n'+ vaccineDate

            binding.vaccinetext.text = vaccinesUpdated
            binding.datetext.text = datesUpdated

            binding.vaccinename.setText("")
            binding.vaccinedate.text = ""

            editor.apply{
                putString("vaccine", vaccinesUpdated)
                putString("date", datesUpdated)
                apply()
            }

        }

        binding.clear.setOnClickListener {

            builder.setTitle("Clearing vaccine history")
                .setMessage("Are you sure you want to delete all vaccine records?")
                .setCancelable(true)
                .setPositiveButton("Yes"){ dialogInterface, it ->
                    binding.vaccinetext.text= ""
                    binding.datetext.text = ""
                }
                .setNegativeButton("Cancel"){ dialogInterface, it ->
                    dialogInterface.cancel()
                }
                .show()
        }


        binding.selectdate.setOnClickListener{
            DatePickerDialog(requireContext(), datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()

        }


    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat =  "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.vaccinedate.text = sdf.format(myCalendar.time)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}