package com.dzenlab.nasajava.data.network.models;

import static com.dzenlab.nasajava.data.network.Constants.DATE_JSON;
import static com.dzenlab.nasajava.data.network.Constants.TITLE_JSON;
import static com.dzenlab.nasajava.data.network.Constants.URL_JSON;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

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
}
