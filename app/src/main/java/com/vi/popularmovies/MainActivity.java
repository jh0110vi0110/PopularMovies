package com.vi.popularmovies;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.vi.popularmovies.database.MovieFavorite;
import com.vi.popularmovies.model.Movie;
import com.vi.popularmovies.utils.Json;
import com.vi.popularmovies.utils.Network;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements PosterRecyclerAdapter.OnPosterListener {

    private static int currentSort;
    private RecyclerView mRecyclerView;
    private Movie[] mMovies;
    private MovieFavorite[] mMovieFavorites = new MovieFavorite[0];

    private PosterRecyclerAdapter mPosterRecyclerAdapter;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
            outState.putParcelableArray("Movies", mMovies);
            outState.putInt("currentSort", currentSort);
            super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView();
        setupViewModel();

        if ( savedInstanceState != null){
            //restore state of network data
            mMovies = (Movie[]) savedInstanceState.getParcelableArray("Movies");
            currentSort = savedInstanceState.getInt("currentSort", R.id.sort_popular);
            setDisplayedSortType(currentSort);
            mPosterRecyclerAdapter.setMovieData(mMovies);
        }else{
            //new creation
            currentSort = R.id.sort_popular;
            setDisplayedSortType(currentSort);
            queryTheMovieDatabase(currentSort);
        }

    }

    public void initializeRecyclerView (){
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_grid);
        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(this,342);
        mRecyclerView.setLayoutManager(autoFitGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mPosterRecyclerAdapter = new PosterRecyclerAdapter(mMovies, this);
        mRecyclerView.setAdapter(mPosterRecyclerAdapter);
    }

    private void clearMovies() {
        if (mMovies != null) {
            mMovies = new Movie[mMovies.length];
        } else {
            mMovies = new Movie[0];
        }
    }

    public void queryTheMovieDatabase(int sortType){
        URL dataUrl = Network.buildDataUrl(sortType);
        new QueryMovieDatabase().execute(dataUrl);
    }

    @Override
    public void onPosterClick(int position) {
        Movie movieToSend = mMovies[position];
        //Navigate to New Activity
        Intent intentToStartDetailActivity = new Intent(this, DetailActivity.class);
        intentToStartDetailActivity.putExtra("Movie", movieToSend);
        startActivity(intentToStartDetailActivity);
    }

    public class QueryMovieDatabase extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL queryUrl = urls[0];
            String queryResult = null;

            try {
                queryResult = Network.getResponseFromHttpUrl(queryUrl);
                return queryResult;
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if ( result != null && result != ""){
                try {
                    mMovies = Json.readMoviesJson(result);
                    mPosterRecyclerAdapter.setMovieData(mMovies);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void setupViewModel(){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, new Observer<MovieFavorite[]>() {
            @Override
            public void onChanged(@Nullable MovieFavorite[] movieFavorites) {
                if (movieFavorites.length >= 0){
                    mMovieFavorites = movieFavorites;
                    if (currentSort == R.id.sort_favorites){
                        mMovies = convertMovieArray(mMovieFavorites);
                        mPosterRecyclerAdapter.setMovieData(mMovies);
                    }
                }
            }
        });

    }

    //Main Menu to Toggle Sort Types
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicked = item.getItemId();
        if (currentSort == itemClicked){
            return true;
        }
        if (itemClicked == R.id.sort_popular){
            currentSort = R.id.sort_popular;
            setDisplayedSortType(R.id.sort_popular);
            //sort by popularity.desc
            queryTheMovieDatabase(R.id.sort_popular);
            return true;
        }else if (itemClicked == R.id.sort_rating){
            currentSort = R.id.sort_rating;
            setDisplayedSortType(R.id.sort_rating);
            //use sort by vote_average.desc
            queryTheMovieDatabase(R.id.sort_rating);
            return true;
        }else if (itemClicked == R.id.sort_favorites){
            currentSort = R.id.sort_favorites;
            setDisplayedSortType(R.id.sort_favorites);
            mMovies = convertMovieArray(mMovieFavorites);
            mPosterRecyclerAdapter.setMovieData(mMovies);

        }
        return super.onOptionsItemSelected(item);
    }

    public void setDisplayedSortType (int sortType){
        if (sortType == R.id.sort_popular){
            setTitle(getString(R.string.app_name) + " - " + getString(R.string.popular));
        }else if (sortType == R.id.sort_rating){
            setTitle(getString(R.string.app_name) + " - " + getString(R.string.top_rated));
        }else if (sortType == R.id.sort_favorites){
            setTitle(getString(R.string.app_name) + " - " + getString(R.string.favorites));
        }
    }

    public Movie[] convertMovieArray( MovieFavorite[] movieFavorites) {
        Movie[] movies = new Movie[movieFavorites.length];

        for (int i = 0; i < movieFavorites.length; i++) {
            //Movie movieConvert = new Movie(movieFavorites[i]);
            movies[i] = new Movie(movieFavorites[i]);
        }
        return movies;
    }

    public MovieFavorite[] convertMovieArray( Movie[] movies) {
        MovieFavorite[] movieFavorites = new MovieFavorite[movies.length];

        for (int i = 0; i < movies.length; i++) {
            //Movie movieConvert = new Movie(movieFavorites[i]);
            movieFavorites[i] = new MovieFavorite(movies[i]);
        }
        return movieFavorites;
    }
}
