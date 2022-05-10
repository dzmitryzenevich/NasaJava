package com.dzenlab.nasajava.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class StatePictureSP {

    private final boolean isOpen;


    public StatePictureSP(boolean isOpen) {

        this.isOpen = isOpen;
    }

    public boolean isOpen() {

        return isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatePictureSP)) return false;
        StatePictureSP that = (StatePictureSP) o;
        return isOpen() == that.isOpen();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen());
    }

    @NonNull
    @Override
    public String toString() {
        return "StatePictureSP{" +
                "isOpen=" + isOpen +
                '}';
    }
}
