package com.dzenlab.nasajava.models;

import androidx.annotation.NonNull;
import java.util.Date;
import java.util.Objects;

public class ItemNet {

    private final int id;

    private final Date date;

    private final String title;

    private final String url;


    public ItemNet(int id, Date date, String title, String url) {

        this.id = id;

        this.date = date;

        this.title = title;

        this.url = url;
    }

    public int getId() {

        return id;
    }

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
        if (!(o instanceof ItemNet)) return false;
        ItemNet itemNet = (ItemNet) o;
        return getId() == itemNet.getId() &&
                Objects.equals(getDate(), itemNet.getDate()) &&
                Objects.equals(getTitle(), itemNet.getTitle()) &&
                Objects.equals(getUrl(), itemNet.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getTitle(), getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}