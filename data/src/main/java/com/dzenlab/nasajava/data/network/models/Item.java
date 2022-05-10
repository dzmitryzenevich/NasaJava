package com.dzenlab.nasajava.data.network.models;

import static com.dzenlab.nasajava.data.network.Constants.DATE_JSON;
import static com.dzenlab.nasajava.data.network.Constants.TITLE_JSON;
import static com.dzenlab.nasajava.data.network.Constants.URL_JSON;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.Objects;

public class Item {

    @SuppressWarnings("unused")
    @SerializedName(DATE_JSON)
    private Date date;

    @SuppressWarnings("unused")
    @SerializedName(TITLE_JSON)
    private  String title;

    @SuppressWarnings("unused")
    @SerializedName(URL_JSON)
    private String url;


    public Date getDate() {

        return date;
    }

    public String getTitle() {

        return title;
    }

    public String getUrl() {

        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getDate(), item.getDate()) &&
                Objects.equals(getTitle(), item.getTitle()) &&
                Objects.equals(getUrl(), item.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getTitle(), getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "Item{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
