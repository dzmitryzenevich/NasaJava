package com.dzenlab.nasajava.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class PagingDataSP {

    private final int data;


    public PagingDataSP(int data) {

        this.data = data;
    }

    public int getData() {

        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagingDataSP)) return false;
        PagingDataSP that = (PagingDataSP) o;
        return getData() == that.getData();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }

    @NonNull
    @Override
    public String toString() {
        return "PagingDataSP{" +
                "data=" + data +
                '}';
    }
}
