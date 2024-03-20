package com.example.envirocarchallengeapp.mapdisplay.popup;

import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This class serves as a data holder for the information displayed in the popup on the map.
 */
public class PopupViewData {

    private final TextView titleTextView;
    private final TextView descriptionTextView;
    private final LinearLayout popupCardLayout;

    /**
     * Constructor to initialize the popup view data.
     *
     * @param titleTextView The TextView reference for the popup title.
     * @param descriptionTextView The TextView reference for the popup description.
     * @param popupCardLayout The LinearLayout reference for the entire popup card layout.
     */
    public PopupViewData(TextView titleTextView, TextView descriptionTextView, LinearLayout popupCardLayout) {
        this.titleTextView = titleTextView;
        this.descriptionTextView = descriptionTextView;
        this.popupCardLayout = popupCardLayout;
    }

    /**
     * Returns the reference to the TextView object used for the popup title.
     *
     * @return The title TextView.
     */
    public TextView getTitleTextView() {
        return titleTextView;
    }

    /**
     * Returns the reference to the TextView object used for the popup description.
     *
     * @return The description TextView.
     */
    public TextView getDescriptionTextView() {
        return descriptionTextView;
    }

    /**
     * Returns the reference to the LinearLayout object representing the entire popup card layout.
     *
     * @return The popup card layout.
     */
    public LinearLayout getPopupCardLayout() {
        return popupCardLayout;
    }
}
