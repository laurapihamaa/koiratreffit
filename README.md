Koirakaverit -application

Overview:

The koirakaverit-application (dog friends in english) idea started with me getting my first puppy. If you know anything about dogs, you might be familiar with the concept of socializing your dog. This means finding suitable friends for your dog to play with and practice their social skills with. Finding dog friends from platforms such as Facebook or Instagram is not the easiest, so while I struggled with finding suitable friends for my puppy, I came up with the idea of an application for dog owners to connect and plan play dates for their dogs.

Architecture, used technologies, UI-design:

The backend of the application is built with Java 21 and SpringBoot, and the frontend is built with React Native. The database uses MongoDB.
I also created a CI-pipeline using GitHub Actions with three steps:
1. Run a CodeQL-analysis
2. Build and run the unit tests
3. create Docker images and push the images to the registry

A UML-diagram of the application looks like this (added on 29.11.2024):

![image](https://github.com/user-attachments/assets/8efbf05d-1151-400f-a2f5-e9ef957fc266)

At this point the dog controller listens to incoming requests and responds to them accordingly. The service retrieves the dog data from the database using the interface to Mongo Repository.
The frontend consists of screens and entities.

The UI follows the very famous tinder-like swiping mechanism, where the user can swipe "cards" where the dog info is placed on and add the dogs as friend pressing a button.
If both of the users have added each other as friend, a possibility for messaging between the users opens.

Plans:

Functionalities that are still left to implement are the user profile creation and authenticating to the app and sending messages to other users.
The authentication will most probably use the JWT-authentication mechanism and for the messaging I am planning to use web sockets. But these are still in planning phase.

Some notes:

*The images are now stored as base64 encoded strings to the database. This is by no means an actual decision I would make in a real-life scenario, but as I didnt want to pay for any cloud storages etc. I created this as a quick solution. In an actual implementation I would use maybe Azure Blob Storage and store the urls of the images to mongo.

*I have focused on making an MVP-type of solution of the app and I am planning to focus on the UI-design more once I have all of the main fucntionalities stored. The style sheet is a mess at the moment (sorry guys) and some things like only having one card on the deck is something I have on the to do -list as the app development makes progress.

*So far the state management done in React seems to be enough but I am still contemplating whether to implement Redux at some point.





