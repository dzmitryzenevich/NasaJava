package com.dzenlab.nasajava.data.sharepref.models;

import androidx.annotation.NonNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateUrlPicture)) return false;
        StateUrlPicture that = (StateUrlPicture) o;
        return isOpen() == that.isOpen() && Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen(), getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "StateUrlPicture{" +
                "isOpen=" + isOpen +
                ", url='" + url + '\'' +
                '}';
    }
}
