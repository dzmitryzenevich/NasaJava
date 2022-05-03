package com.dzenlab.nasajava.data.database.models;

import static com.dzenlab.nasajava.data.database.Constants.ID_COLUMN_NAME;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import java.util.Objects;

public class ItemDelete {

    @ColumnInfo(name = ID_COLUMN_NAME)
    private long id;


    public ItemDelete(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDelete that = (ItemDelete) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public String toString() {
        return "ItemDelete{" +
                "id=" + id +
                '}';
    }
}
