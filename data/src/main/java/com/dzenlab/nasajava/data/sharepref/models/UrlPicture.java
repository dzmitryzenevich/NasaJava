package com.dzenlab.nasajava.data.sharepref.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class UrlPicture {

    private final String url;


    public UrlPicture(String url) {

        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UrlPicture)) return false;
        UrlPicture that = (UrlPicture) o;
        return Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "UrlPicture{" +
                "url='" + url + '\'' +
                '}';
    }
}
