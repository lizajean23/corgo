
# CORGO
![cover](https://user-images.githubusercontent.com/115420570/213478711-894029db-d2b2-4588-8040-dd621627d591.jpg)
Introducing CORGO! The first social media platform dedicated entirely to our best friends - our pets! Had a bad day? Simply open up CORGO, sit back and enjoy scrolling through the cutest feed you'll ever find!

## OVERVIEW

![srr](https://user-images.githubusercontent.com/115420570/213481080-4f0480d3-5922-42c9-827d-eecc4c5c29f8.png)

CORGO is an android social media app which came to life thanks to the Firebase Authentication, Realtime Database and Storage services. After registration, the users can sign into their account, edit their personal profile, upload posts with pictures and see other user's posts.

![scren2](https://user-images.githubusercontent.com/115420570/213482928-48ff2d6c-2566-4cb9-9d16-bad7faea5943.png)

## WHAT IS POSSIBLE IN OUR APP?

- Users can change their profile picture from the profile.
- Users can upload posts with description and selected picture from their gallery.
- After uplpoading the post, it will appear on the feed with the user's profile picture and username.
- Users can change password from their profile or request a password reset from the login screen.
- Users can keep track of their pet's important vaccines on the Vaccine page.

## TECHNICAL DETAILS

- Navigation component is used to navigate between the login fragments.
- ViewPager2 is used to navigate between the app's main fragments.
- RecyclerView is used in the feed fragment for displaying user posts.
- Profile pictures and post pictures are stored in the Firebase Storage.
- Posts: description, the current user & their profile picture, and the uploaded image is stored in Firebase Realtime Database.
- The vaccines fragment uses shared preferences to store users entered data.
- Alert dialogs are used on sensitive cases such as logging out from the account, changing the password, or deleting the vaccine records.
