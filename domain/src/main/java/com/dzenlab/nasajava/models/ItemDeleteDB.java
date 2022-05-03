package com.dzenlab.nasajava.models;

import java.util.Objects;

public class ItemDeleteDB {

    private final long id;


    public ItemDeleteDB(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ItemDeleteDB that = (ItemDeleteDB) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ItemDelete{" +
                "id=" + id +
                '}';
    }
}
