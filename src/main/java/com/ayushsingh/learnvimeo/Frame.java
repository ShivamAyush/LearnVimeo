package com.ayushsingh.learnvimeo;

import com.google.gson.annotations.SerializedName;

public class Frame {

    private String title;

    private String description;

    @SerializedName("html")
    private String address;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
