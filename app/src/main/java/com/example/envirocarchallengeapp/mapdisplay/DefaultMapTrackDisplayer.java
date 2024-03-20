package com.example.envirocarchallengeapp.mapdisplay;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;


import com.example.envirocarchallengeapp.mapdisplay.popup.PopupViewData;
import com.example.envirocarchallengeapp.track.TrackData;

import org.maplibre.android.annotations.Marker;
import org.maplibre.android.annotations.MarkerOptions;
import org.maplibre.android.annotations.PolylineOptions;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapView;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DefaultMapTrackDisplayer implements MapTrackDisplayer {


    private PopupViewData view;
    /**
     * Displays the provided track data on the map.
     *
     * @param trackData The TrackData object containing the track information.
     * @param mapView The MapView reference where the track will be displayed.
     * @param viewdata The PopupViewData object for managing popup content.
     */
    @Override
    public void displayTrack(TrackData trackData, MapView mapView, PopupViewData viewdata) {
        System.out.println("Caling draw track");
        view=viewdata;
        drawTrack(trackData.getLatLngList(), mapView);
        addMarker(trackData, mapView);
        placeCamera(trackData.getOrigin(), mapView);
    }
    /**
     * Draws the track path on the map using polylines.
     *
     * @param latlngList A list of LatLng objects representing the track path.
     * @param mapView The MapView reference where the track will be drawn.
     */
    public void drawTrack(List<LatLng> latlngList, MapView mapView) {
        mapView.getMapAsync(map -> {
            for (int i = 1; i < latlngList.size(); i++) {
                PolylineOptions polylineOptions = new PolylineOptions()
                        .add(latlngList.get(i - 1), latlngList.get(i))  // Create line segments between coordinates
                        .color(Color.parseColor("#32CD32"))  // Set line color
                        .width(14f);  // Set line width
                map.addPolyline(polylineOptions);  // Add the line segment to the map
            }

        });
    }
    /**
     * Adds markers at the origin and destination points of the track with click listeners for popup display.
     *
     * @param trackData The TrackData object containing origin and destination LatLng.
     * @param mapView The MapView reference where the markers will be added.
     */
    public void addMarker(TrackData trackData, MapView mapView) {
        mapView.getMapAsync(map -> {

            MarkerOptions originMarker = new MarkerOptions()
                    .position(trackData.getOrigin());
            MarkerOptions DestinationMarker = new MarkerOptions()
                    .position(trackData.getDestination());
            Marker origin = map.addMarker(originMarker);
            Marker destination = map.addMarker(DestinationMarker);

            map.setOnMarkerClickListener(marker -> {
                if (marker.equals(origin)) {
                    onMarkerTouch(trackData.getStartTime(), origin, "origin");
                    return true; // Consume the event to prevent it from being passed to other listeners
                } else if (marker.equals(destination)) {
                    onMarkerTouch(trackData.getEndTime(), destination, "destination");
                    return true; // Consume the event to prevent it from being passed to other listeners
                }

                return false; // Let other listeners handle the event
            });

        });
        // Handle map touch to hide popup (optional)
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onMapTouch();
                return false; // Let other listeners handle the event
            }
        });
    }
    /**
     * Positions the camera on the origin point of the track with a zoom level of 16.
     *
     * @param origin The LatLng object representing the origin point.
     * @param mapView The MapView reference for camera manipulation.
     */
    public void placeCamera(LatLng origin, MapView mapView) {
        mapView.getMapAsync(map -> {
            CameraPosition targetCameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(origin))
                    .zoom(16.0)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(targetCameraPosition), 5000);
        });
    }

    /**
     * Updates the popup content (title and description) based on the tapped marker (origin/destination)
     * and formatted time information.
     *
     * @param time The timestamp string of the tapped marker (start or end time).
     * @param marker The Marker object that was tapped.
     * @param markertype A string indicating the marker type ("origin" or "destination").
     */
    public void onMarkerTouch(String time, Marker marker, String markertype) {
        if(markertype.equals("origin")){
            view.getDescriptionTextView().setText("Clicked on : " + format_time(time));
            view.getTitleTextView().setText(markertype);
            view.getPopupCardLayout().setVisibility(View.VISIBLE);
        }
        else{
            view.getDescriptionTextView().setText("Clicked on : " + format_time(time));
            view.getTitleTextView().setText(markertype);
            view.getPopupCardLayout().setVisibility(View.VISIBLE);
        }
    }
    public void onMapTouch(){
        view.getPopupCardLayout().setVisibility(View.GONE);
    }
    public String format_time(String time){
        if (time== null) {
            throw new IllegalArgumentException("Invalid UTC time string provided.");
        }

        try {
            // Parse the string into a DateTimeFormatter object
            DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(time, formatter);

            // Extract hour, minute, second, year, month, and day information
            int hour = zonedDateTime.getHour();
            int minute = zonedDateTime.getMinute();
            int second = zonedDateTime.getSecond();
            int year = zonedDateTime.getYear();
            int month = zonedDateTime.getMonthValue();
            int day = zonedDateTime.getDayOfMonth();

            // Convert hour to 12-hour format with AM/PM
            String amPm = hour < 12 ? "AM" : "PM";
            hour = hour % 12;
            if (hour == 0) {
                hour = 12;  // Handle midnight cases for 12-hour format
            }

            // Format the date and time components
            String formattedTime = String.format("%02d:%02d:%02d %s", hour, minute, second, amPm);
            String formattedDate = String.format("%d/%d/%d", day, month, year);

            // Combine formatted time, date, and UTC indicator
            return String.format("%s on date %s UTC", formattedTime, formattedDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid UTC time format. Please check the format and try again.", e);
        }
    }

}
