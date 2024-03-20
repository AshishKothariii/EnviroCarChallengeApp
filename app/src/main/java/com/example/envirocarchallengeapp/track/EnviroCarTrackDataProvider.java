package com.example.envirocarchallengeapp.track;
import android.content.Context;


import com.example.envirocarchallengeapp.R;
import com.example.envirocarchallengeapp.api.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.maplibre.android.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the TrackDataProvider interface to fetch track data from the EnviroCar API.
 */
public class EnviroCarTrackDataProvider implements TrackDataProvider {
    private final String TRACK_DATA_BASE_API = "https://envirocar.org/api/stable/tracks/";

    /**
     * Fetches track data from the EnviroCar API based on a track ID from string resources.
     *
     * @param context The application context.
     * @return A TrackData object containing the fetched data, or null on error.
     */
    @Override
    public TrackData getTrackData(Context context) {
        // Track data URL construction using base API and track ID from string resources
        String trackDataFinalApi = TRACK_DATA_BASE_API + context.getString(R.string.track_id);

        try {
            // Fetch JSON data synchronously (consider asynchronous approach for better performance)
            JSONObject jsonObject = ApiClient.fetchDataSync(trackDataFinalApi);

            // Parse JSON response to extract coordinates, timestamps
            JSONArray featuresArray = jsonObject.getJSONArray("features");
            List<LatLng> latLngList = new ArrayList<>();
            String startTime = null;
            String endTime = null;

            for (int i = 0; i < featuresArray.length(); i++) {
                JSONObject feature = featuresArray.getJSONObject(i);
                JSONObject geometry = feature.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");

                // Extract latitude and longitude from coordinates array
                latLngList.add(new LatLng(coordinates.getDouble(1), coordinates.getDouble(0)));

                if (i == 0) {
                    startTime = feature.getJSONObject("properties").getString("time");
                }
                if (i == featuresArray.length() - 1) {
                    endTime = feature.getJSONObject("properties").getString("time");
                }
            }

            // Return TrackData object with parsed data
            return new TrackData(latLngList, startTime, endTime);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}