package com.example.envirocarchallengeapp.track;
import org.maplibre.android.geometry.LatLng;

import java.util.List;

public class TrackData {
    private final List<LatLng> latLngList;
    private final String startTime;
    private final String endTime;

    private final LatLng origin;

    private final LatLng destination;

    /**
     * Constructor to initialize track data.
     *
     * @param latLngList A list of LatLng objects representing the track path.
     * @param startTime The timestamp (as a String) when the track recording began.
     * @param endTime The timestamp (as a String) when the track recording ended.
     */
    public TrackData(List<LatLng> latLngList, String startTime, String endTime) {
        this.latLngList = latLngList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.origin=latLngList.get(0);
        this.destination=latLngList.get(latLngList.size()-1);
    }


    /**
     * Returns the unmodifiable list of LatLng objects representing the track path.
     *
     * @return The list of LatLng points.
     */
    public List<LatLng> getLatLngList() {
        return latLngList;
    }

    /**
     * Returns the starting timestamp of the track recording as a String.
     *
     * @return The starting timestamp.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Returns the ending timestamp of the track recording as a String.
     *
     * @return The ending timestamp.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns the LatLng object representing the starting point of the track.
     *
     * @return The origin LatLng.
     */
    public LatLng getOrigin() {
        return origin;
    }

    /**
     * Returns the LatLng object representing the ending point of the track.
     *
     * @return The destination LatLng.
     */
    public LatLng getDestination() {
        return destination;
    }
}
