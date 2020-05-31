package ru.bdim.pictures.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> addAll(List<PictureEntity> pictureEntity);

    @Query("DELETE FROM pictures")
    Single<Integer> deleteAll();

    @Query("SELECT * FROM pictures")
    Observable<List<PictureEntity>> getAll();
}