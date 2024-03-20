package com.example.envirocarchallengeapp.track;

import android.content.Context;

/**
 * This interface defines a contract for classes that provide track data.
 * It promotes loose coupling by allowing different implementations for track data retrieval.
 */

public interface TrackDataProvider {
    /**
     * This method is responsible for fetching track data and returning it as a TrackData object.
     *
     * @param context The application context.
     * @return A TrackData object containing the fetched data, or null on error.
     */
    public TrackData getTrackData(Context context);
}
