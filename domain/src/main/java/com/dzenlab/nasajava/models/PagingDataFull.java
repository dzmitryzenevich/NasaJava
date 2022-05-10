package com.dzenlab.nasajava.models;

import androidx.annotation.NonNull;
import java.util.List;
import java.util.Objects;

public class PagingDataFull {

    private int page;

    private Integer prevPage;

    private Integer nextPage;

    private List<ItemNet> list;


    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }

    public Integer getPrevPage() {

        return prevPage;
    }

    public void setPrevPage(Integer prevPage) {

        this.prevPage = prevPage;
    }

    public Integer getNextPage() {

        return nextPage;
    }

    public void setNextPage(Integer nextPage) {

        this.nextPage = nextPage;
    }

    public List<ItemNet> getList() {

        return list;
    }

    public void setList(List<ItemNet> list) {

        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagingDataFull)) return false;
        PagingDataFull that = (PagingDataFull) o;
        return getPage() == that.getPage() &&
                Objects.equals(getPrevPage(), that.getPrevPage()) &&
                Objects.equals(getNextPage(), that.getNextPage()) &&
                Objects.equals(getList(), that.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), getPrevPage(), getNextPage(), getList());
    }

    @NonNull
    @Override
    public String toString() {
        return "PagingDataFull{" +
                "page=" + page +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                ", list=" + list +
                '}';
    }
}
