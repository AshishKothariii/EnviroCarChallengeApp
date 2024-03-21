package com.example.envirocarchallengeapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.envirocarchallengeapp.mapdisplay.DefaultMapTrackDisplayer;
import com.example.envirocarchallengeapp.mapdisplay.MapTrackDisplayer;
import com.example.envirocarchallengeapp.mapdisplay.popup.PopupViewData;
import com.example.envirocarchallengeapp.track.EnviroCarTrackDataProvider;
import com.example.envirocarchallengeapp.track.TrackData;
import com.example.envirocarchallengeapp.track.TrackDataProvider;


import org.maplibre.android.MapLibre;
import org.maplibre.android.maps.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private Button myTrackButton;
    private TrackDataProvider trackDataprovider;
    private MapTrackDisplayer trackDisplayer;
    private LinearLayout popupCardLayout;
    private TextView titleTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize MapLibre instance
        MapLibre.getInstance(this);

        // Inflate the layout and set the content view
        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.activity_main, null);
        setContentView(rootView);

        // Find map view reference and set base map style
        mapView = rootView.findViewById(R.id.mapView);
        mapView.getMapAsync(map -> {
            String mapApiUrl = getString(R.string.map_api_url);
            String apiKey = getString(R.string.api_key);
            String mapId = "openstreetmap";
            String styleUrl = String.format("%s%s/style.json?key=%s", mapApiUrl, mapId, apiKey);
            map.setStyle(styleUrl);
        });

        // Find references to UI elements
        popupCardLayout = rootView.findViewById(R.id.popupCardLayout);
        titleTextView = rootView.findViewById(R.id.titleTextView);
        descriptionTextView = rootView.findViewById(R.id.descriptionTextView);
        myTrackButton = rootView.findViewById(R.id.myTrack);

        // Initialize data provider and displayer objects
        trackDataprovider = new EnviroCarTrackDataProvider();
        trackDisplayer = new DefaultMapTrackDisplayer();

        // Set click listener for "My Track" button
        myTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch track data in a separate method
                TrackData trackData = getTrackData();

                // Display the fetched track data on the map
                if (trackData != null) {
                    displayTrack(trackData);
                } else {
                    // Handle potential errors during data retrieval (e.g., display error message)
                    System.out.println("Error fetching track data!");
                }

                // Hide the button after fetching data
                myTrackButton.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Fetches track data using the EnviroCarTrackDataProvider.
     *
     * @return A TrackData object containing the fetched data, or null on error.
     */
    private TrackData getTrackData() {
        return trackDataprovider.getTrackData(this);
    }

    /**
     * Displays the provided track data on the map using the DefaultMapTrackDisplayer.
     *
     * @param trackData The TrackData object containing the track information.
     */
    private void displayTrack(TrackData trackData) {
        trackDisplayer.displayTrack(trackData, mapView,
                new PopupViewData(titleTextView, descriptionTextView, popupCardLayout));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mapView.onSaveInstanceState(outState);
    }
}