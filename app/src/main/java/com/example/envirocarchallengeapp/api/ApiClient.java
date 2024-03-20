package com.example.envirocarchallengeapp.api;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    /**
     * Fetches data from a URL synchronously. This method blocks the UI thread.
     * Consider using asynchronous techniques for better performance.
     *
     * @param apiUrl The URL of the API endpoint.
     * @return A JSONObject containing the fetched data, or null on error.
     * @throws IOException  If there's an error reading from the network stream.
     * @throws JSONException If there's an error parsing the JSON response.
     */
    public static JSONObject fetchDataSync(final String apiUrl) {
        final JSONObject[] jsonObject = {null};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(apiUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");

                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuilder buffer = new StringBuilder();
                    if (inputStream == null) {
                        // Nothing to do
                        return;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line).append("\n");
                    }

                    if (buffer.length() == 0) {
                        // Empty response
                        return;
                    }

                    String jsonString = buffer.toString();
                    jsonObject[0] = new JSONObject(jsonString);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread.start();
        try {
            thread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return jsonObject[0];
    }
}
