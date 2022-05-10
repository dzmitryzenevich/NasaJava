package com.dzenlab.nasajava.data.database;

import static com.dzenlab.nasajava.data.database.Constants.DATABASE_VERSION;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.dzenlab.nasajava.data.database.converters.DateConverters;
import com.dzenlab.nasajava.data.database.dao.ItemDao;
import com.dzenlab.nasajava.data.database.entities.ItemEntity;

@TypeConverters({DateConverters.class})
@Database(entities = {ItemEntity.class}, version = DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao getItemDao();
}