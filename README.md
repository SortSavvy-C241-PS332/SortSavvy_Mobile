# SortSavvy Mobile

Capstone Project - SortSavvy

## About
SortSavvy is a mobile application designed to educate Indonesian youth on proper waste sorting and recycling practices. By leveraging machine learning for waste classification and providing interactive educational content, the app aims to raise awareness and promote sustainable waste management behaviours. Users can take a picture of waste for instant classification, engage with quizzes, and earn badges for their progress. We aim to foster early environmental awareness and contribute to a sustainable future.

## Features
- User authentication
- Image classification (custom TensorFlow Lite model)
- Challenge (Quiz)
- Educational content
- Offline mode

## Screenshots
[on progress]

## Installation
To run this project, follow these steps:

1. **Clone the repository:**
   ```sh
   git clone https://github.com/SortSavvy-C241-PS332/SortSavvy_Mobile.git
   ```
2. **Open the project in Android Studio:**
   - File -> Open -> Select the project folder
3. **Build the project:**
   - Click on "Build" -> "Make Project"
4. **Run the application:**
   - Click on "Run" -> "Run 'app'"

## Requirements
- Android Studio Hedgehog or newer
- Kotlin 1.19
- Open JDK 17
- Android SDK 24 or newer

## Usage
1. Open the app on your Android device.
2. Please create a new account and login to your account
3. Engange with all the features on the app

## Project Structure
```
SortSavvy_Mobile/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/bangkit/sortsavvy/
│   │   │   |   ├── adapter
│   │   │   |   ├── data
│   │   │   |   ├── di
│   │   │   |   ├── factory
│   │   │   |   ├── utils
│   │   │   |   ├── views
│   │   │   └── res/
│   │   │   └── ml/
│   ├── build.gradle
├── gradle/
├── .gitignore
├── build.gradle.kts
├── settings.gradle.kts
```

## Contributors
- [uqisya](https://github.com/uqisya)
- [Brigitta Christa](https://github.com/brigittable)

## Acknowledgments
- Special thanks to our instructors, mentors, and Dicoding Academy.
- Thanks to the open-source community for the libraries and tools.
