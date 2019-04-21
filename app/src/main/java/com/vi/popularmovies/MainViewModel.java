package com.vi.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vi.popularmovies.database.MovieDatabase;
import com.vi.popularmovies.database.MovieFavorite;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private LiveData<MovieFavorite[]> movies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        MovieDatabase database = MovieDatabase.getInstance(this.getApplication());
        Log.d(TAG, "MainViewModel: Retrieving movies from db");
        movies = database.movieDao().loadAllMovies();
    }

    public LiveData<MovieFavorite[]> getMovies() { return movies; }
}
