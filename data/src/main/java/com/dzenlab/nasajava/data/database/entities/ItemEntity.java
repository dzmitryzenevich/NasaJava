package com.dzenlab.nasajava.data.database.entities;

import static com.dzenlab.nasajava.data.database.Constants.DATE_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.ID_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.ITEM_TABLE_NAME;
import static com.dzenlab.nasajava.data.database.Constants.TITLE_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.URL_COLUMN_NAME;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = ITEM_TABLE_NAME)
public class ItemEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN_NAME)
    private final long id;

    @NonNull
    @ColumnInfo(name = DATE_COLUMN_NAME)
    private final Date date;

    @NonNull
    @ColumnInfo(name = TITLE_COLUMN_NAME)
    private final String title;

    @NonNull
    @ColumnInfo(name = URL_COLUMN_NAME)
    private final String url;


    public ItemEntity(long id, @NonNull Date date, @NonNull String title, @NonNull String url) {

        this.id = id;

        this.date = date;

        this.title = title;

        this.url = url;
    }

    public long getId() {

        return id;
    }

    @NonNull
    public Date getDate() {

        return date;
    }

    @NonNull
    public String getTitle() {

        return title;
    }

    @NonNull
    public String getUrl() {

        return url;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ItemEntity)) return false;

        ItemEntity that = (ItemEntity) o;

        return getId() == that.getId() && Objects.equals(getDate(),
                that.getDate()) && Objects.equals(getTitle(),
                that.getTitle()) && Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getTitle(), getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}