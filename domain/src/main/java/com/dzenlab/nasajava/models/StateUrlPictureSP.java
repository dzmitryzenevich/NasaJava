package com.dzenlab.nasajava.models;

import org.jetbrains.annotations.NotNull;

public class StateUrlPictureSP {

    private final boolean isOpen;

    @NotNull
    private final String url;


    public StateUrlPictureSP(boolean isOpen, @NotNull String url) {

        this.isOpen = isOpen;

        this.url = url;
    }

    public boolean isOpen() {

        return isOpen;
    }

    @NotNull
    public String getUrl() {

        return url;
    }
}
