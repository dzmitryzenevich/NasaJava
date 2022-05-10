package com.dzenlab.nasajava.data.mapper;

import com.dzenlab.nasajava.data.database.models.Item;
import com.dzenlab.nasajava.data.sharepref.models.StateUrlPicture;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Response;

public class Mapper {

    public static List<ItemNet> getItemListFromNetList(
            Response<List<com.dzenlab.nasajava.data.network.models.Item>> response) {

        if(response.isSuccessful()) {

            List<com.dzenlab.nasajava.data.network.models.Item> list = response.body();

            if(list != null) {

                List<ItemNet> itemNetList = new ArrayList<>();

                for(com.dzenlab.nasajava.data.network.models.Item item : list) {

                    ItemNet itemNet = new ItemNet(0,
                            item.getDate(),
                            item.getTitle(),
                            item.getUrl() != null ? item.getUrl() : "");

                    itemNetList.add(itemNet);
                }

                return itemNetList;

            } else {

                return null;
            }

        } else {

            return null;
        }
    }

    public static List<ItemNet> getItemListFromDBList(List<Item> list) {

        List<ItemNet> itemNetList = new ArrayList<>();

        for(Item item : list) {

            ItemNet itemNet = new ItemNet(
                    item.getId(),
                    item.getDate(),
                    item.getTitle(),
                    item.getUrl());

            itemNetList.add(itemNet);
        }

        return itemNetList;
    }

    public static List<Item> getItemDBListFromItemList(List<ItemNet> list) {

        List<Item> itemList = new ArrayList<>();

        for(ItemNet itemNet : list) {

            Item item = new Item(
                    itemNet.getId(),
                    itemNet.getDate(),
                    itemNet.getTitle(),
                    itemNet.getUrl());

            itemList.add(item);
        }

        return itemList;
    }

    public static StateUrlPictureSP getStateUrlPictureSP(StateUrlPicture stateUrlPicture) {

        return new StateUrlPictureSP(
                stateUrlPicture.isOpen(),
                stateUrlPicture.getUrl());
    }
}