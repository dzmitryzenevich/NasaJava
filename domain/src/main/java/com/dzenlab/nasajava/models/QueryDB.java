package com.dzenlab.nasajava.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class QueryDB {

    private final int limit;

    private final int offset;


    public QueryDB(int limit, int offset) {

        this.limit = limit;

        this.offset = offset;
    }

    public int getLimit() {

        return limit;
    }

    public int getOffset() {

        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QueryDB)) return false;
        QueryDB queryDB = (QueryDB) o;
        return getLimit() == queryDB.getLimit() && getOffset() == queryDB.getOffset();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLimit(), getOffset());
    }

    @NonNull
    @Override
    public String toString() {
        return "QueryDB{" +
                "limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
