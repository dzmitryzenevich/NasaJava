package com.dzenlab.nasajava.data.database.converters;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;
import java.util.Date;

@ProvidedTypeConverter
public class DateConverters {

    @TypeConverter
    public Date fromTimestamp(Long value) {

        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {

        return date == null ? null : date.getTime();
    }
}
