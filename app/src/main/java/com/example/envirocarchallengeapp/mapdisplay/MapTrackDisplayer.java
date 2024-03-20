package com.example.envirocarchallengeapp.mapdisplay;

import com.example.envirocarchallengeapp.mapdisplay.popup.PopupViewData;
import com.example.envirocarchallengeapp.track.TrackData;

import org.maplibre.android.maps.MapView;
/**
 * This interface defines a contract for classes that display tracks on a map.
 * It promotes loose coupling by allowing different map implementations
 * and decoupling data retrieval from visualization logic.
 */
public interface MapTrackDisplayer {
    /**
     * This method displays the provided track data on the map.
     * It takes the track data, the map view reference, and an optional view data object
     * (likely for popups) as arguments.
     *
     * @param trackData The TrackData object containing the track information.
     * @param mapView The MapView reference where the track will be displayed.
     * @param viewData An optional view data object for additional display elements (e.g., popups).
     */
    public void displayTrack(TrackData trackData, MapView mapView, PopupViewData viewData);
}
