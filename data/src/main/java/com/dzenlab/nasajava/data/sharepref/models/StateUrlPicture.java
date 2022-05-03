package com.dzenlab.nasajava.data.sharepref.models;

public class StateUrlPicture {

    private final boolean isOpen;

    private final String url;


    public StateUrlPicture(boolean isOpen, String url) {

        this.isOpen = isOpen;

        this.url = url;
    }

    public boolean isOpen() {

        return isOpen;
    }

    public String getUrl() {

        return url;
    }
}
