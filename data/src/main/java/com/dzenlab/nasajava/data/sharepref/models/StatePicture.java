package com.dzenlab.nasajava.data.sharepref.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class StatePicture {

    private final boolean isOpen;


    public StatePicture(boolean isOpen) {

        this.isOpen = isOpen;
    }

    public boolean isOpen() {

        return isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatePicture)) return false;
        StatePicture that = (StatePicture) o;
        return isOpen() == that.isOpen();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOpen());
    }

    @NonNull
    @Override
    public String toString() {
        return "StatePicture{" +
                "isOpen=" + isOpen +
                '}';
    }
}
