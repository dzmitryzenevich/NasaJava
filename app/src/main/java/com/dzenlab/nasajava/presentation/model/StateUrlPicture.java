package com.dzenlab.nasajava.presentation.model;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class StateUrlPicture {

    private final boolean isOpen;

    @NotNull
    private final String url;


    public StateUrlPicture(boolean isOpen, @NotNull String url) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateUrlPicture)) return false;
        StateUrlPicture that = (StateUrlPicture) o;
        return isOpen() == that.isOpen() && getUrl().equals(that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen(), getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "StateUrlPictureSP{" +
                "isOpen=" + isOpen +
                ", url='" + url + '\'' +
                '}';
    }
}
