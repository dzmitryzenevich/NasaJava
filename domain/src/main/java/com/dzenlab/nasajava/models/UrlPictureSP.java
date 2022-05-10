package com.dzenlab.nasajava.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class UrlPictureSP {

    private final String url;


    public UrlPictureSP(String url) {

        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UrlPictureSP)) return false;
        UrlPictureSP that = (UrlPictureSP) o;
        return Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "UrlPictureSP{" +
                "url='" + url + '\'' +
                '}';
    }
}
