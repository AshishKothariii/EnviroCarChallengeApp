# Testing EnviroCarChallengeApp

## 1. Clone this GitHub repository to your local machine using the following command:

- git clone https://github.com/AshishKothariii/EnviroCarChallengeApp.git

## 2. Opening the Project in Android Studio:

### 2.1 Launch Android Studio.

- Choose "Open an existing Android Studio project" from the welcome screen.

- Navigate to the directory where you cloned the repository and select the project folder.

- Click "OK" to open the project in Android Studio.

## 3. Replacing MapLibre API Key:

- Navigate to EnviroCarChallengeApp/app/src/main/res/values/strings.xml.

- Locate the maplibre_api_key placeholder and replace it with your actual MapLibre API key.

- Save the file after making the changes.

## 4. Testing with your own track (Optional)

- Create an enviroCar account from within the Android app or the web application, if you already have a acoount go to next step.

- Start the enviroCar Android app and record a track in your city in GPS-only mode.

- Upload your track to the enviroCar server from within the Android app.

- Now go to EnviroCar.org on web Browser get the track id of the recorded path.

- Now go to EnviroCarChallengeApp/app/src/main/res/values/strings.xml and replace the Track_id Place Holder with your track id.

## 5. Building and Deploying the App:

- Build the project in Android Studio by clicking on the "Build" menu and selecting "Make Project".

- Connect your Android smartphone to your computer using a USB cable.

- Enable USB debugging on your smartphone and ensure it's recognized by Android Studio.

- Select your smartphone as the deployment target and click on the green play button to deploy the app.

- Once deployment is complete, you should see the app icon on your smartphone's home screen or app drawer.

## 6. Testing the App:

- Open the app on your smartphone by tapping on its icon.

- Follow the testing instructions provided in the previous section to test the app's functionality.

- Verify that the track route, origin, and destination markers are displayed correctly on the map.

- Ensure that clicking on markers displays popup cards with relevant information.

- Test other features and interactions within the app.
