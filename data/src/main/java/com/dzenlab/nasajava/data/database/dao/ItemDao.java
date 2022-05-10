package com.dzenlab.nasajava.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import static com.dzenlab.nasajava.data.database.Constants.DATE_COLUMN_NAME;
import static com.dzenlab.nasajava.data.database.Constants.ITEM_TABLE_NAME;
import com.dzenlab.nasajava.data.database.entities.ItemEntity;
import com.dzenlab.nasajava.data.database.models.Item;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ItemDao {

    @Query("SELECT COUNT(*) FROM " + ITEM_TABLE_NAME)
    Single<Integer> getCount();

    @Query("SELECT * FROM " + ITEM_TABLE_NAME +
            " ORDER BY " + DATE_COLUMN_NAME + " ASC" +
            " LIMIT :limit OFFSET :offset")
    List<Item> getAll(int limit, int offset);

    @Insert(entity = ItemEntity.class)
    Completable insertAll(List<Item> list);

    @Delete(entity = ItemEntity.class)
    Completable deleteItem(Item item);
}
