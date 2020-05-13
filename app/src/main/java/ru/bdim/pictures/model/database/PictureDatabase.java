package ru.bdim.pictures.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PictureEntity.class}, version = 1, exportSchema = false)
public abstract class PictureDatabase extends RoomDatabase {
    public abstract PictureDao getDao();

}
