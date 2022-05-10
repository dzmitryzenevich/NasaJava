package com.dzenlab.nasajava.data.sharepref.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class PagingData {

    private final int data;


    public PagingData(int data) {

        this.data = data;
    }

    public int getData() {

        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagingData)) return false;
        PagingData that = (PagingData) o;
        return getData() == that.getData();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }

    @NonNull
    @Override
    public String toString() {
        return "PagingData{" +
                "data=" + data +
                '}';
    }
}
