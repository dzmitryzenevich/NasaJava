package com.dzenlab.nasajava.data.database.models;

import static com.dzenlab.nasajava.data.database.Constants.DATE_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.ID_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.TITLE_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.URL_COLUMN_NAME;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import java.util.Date;
import java.util.Objects;

public class Item {

    @ColumnInfo(name = ID_COLUMN_NAME)
    private long id;

    @ColumnInfo(name = DATE_COLUMN_NAME)
    private final Date date;

    @ColumnInfo(name = TITLE_COLUMN_NAME)
    private final String title;

    @ColumnInfo(name = URL_COLUMN_NAME)
    private final String url;


    public Item(long id, Date date, String title, String url) {

        this.id = id;

        this.date = date;

        this.title = title;

        this.url = url;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
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

        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return getId() == item.getId() && Objects.equals(getDate(),
                item.getDate()) && Objects.equals(getTitle(),
                item.getTitle()) && Objects.equals(getUrl(), item.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getTitle(), getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemDB{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}