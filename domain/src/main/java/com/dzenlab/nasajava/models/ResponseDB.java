package com.dzenlab.nasajava.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class ResponseDB {

    @NotNull
    private final List<ItemNet> list;

    @Nullable
    private final String error;


    public ResponseDB(@NotNull List<ItemNet> list, @Nullable String error) {

        this.list = list;

        this.error = error;
    }

    @NotNull
    public List<ItemNet> getList() {

        return list;
    }

    @Nullable
    public String getError() {

        return error;
    }
}
