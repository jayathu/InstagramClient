# Project 1 - Instagram Client

Instagram Client is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: 8-10 hours spent in total

## User Stories

The following **required** functionality is completed:

* [ X ] User can **scroll through current popular photos** from Instagram
* [ X ] For each photo displayed, user can see the following details:
* [ X ] Graphic, Caption, Username
* [ X ] Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [ X ] User can **pull-to-refresh** popular stream to get the latest popular photos
* [ X ] Show latest comments for each photo
* [ X ] Display each photo with the same style and proportions as the real Instagram
* [ X ] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [ X ] Display a nice default placeholder graphic for each image during loading
* [ X ] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [ X ] Show last 2 comments for each photo
* [ ] Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] Allow video posts to be played in full-screen using the VideoView

The following **additional** features are implemented:

* [ X ] Implemented View Holder pattern to optimize look up time
* [ X ] Applied Butterknife annotation libraries for looking up views (rather than doing by findViewById)

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Butterknife Annotation]  - http://jakewharton.github.io/butterknife/ - Field and method binding for Android Views
- [Rounded Image Views] - https://github.com/vinc3m1/RoundedImageView - ImageView that supports rounded corners

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/bMrXUEp.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

- Most challenging part was the UI design - specifically in making smart choices between Linear and Relative layouts
- Debugging became challenging when the debugger failed to launch properly. App just wont pause at breakpoints and made troubleshooting very hard and tedious. 


## License

    Copyright [2016] [Jayashree Nagarajan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
