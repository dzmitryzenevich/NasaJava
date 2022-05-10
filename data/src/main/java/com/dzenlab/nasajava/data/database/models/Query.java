package com.dzenlab.nasajava.data.database.models;

import androidx.annotation.NonNull;
import java.util.Objects;

public class Query {

    private final int limit;

    private final int offset;


    public Query(int limit, int offset) {

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
        if (!(o instanceof Query)) return false;
        Query query = (Query) o;
        return getLimit() == query.getLimit() && getOffset() == query.getOffset();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLimit(), getOffset());
    }

    @NonNull
    @Override
    public String toString() {
        return "Query{" +
                "limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
