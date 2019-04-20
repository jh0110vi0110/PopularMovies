package com.vi.popularmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;



public interface MovieDao {
    @Query("SELECT * FROM movieFavorite ORDER BY databaseId")
    LiveData<MovieFavorite[]> loadAllMovies();

    @Insert
    void insertMovie(MovieFavorite favMovie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(MovieFavorite favMovie);

    @Delete
    void deleteMovie(MovieFavorite favMovie);

    @Query("SELECT * FROM movieFavorite WHERE databaseId = :id")
    MovieFavorite loadMovieById(int id);


}
