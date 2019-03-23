package com.vi.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.vi.popularmovies.utils.Json;
import com.vi.popularmovies.utils.Network;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PosterRecyclerAdapter.OnPosterListener {
    private RecyclerView mRecyclerView;
    private Movie[] mMovies;
    //private ArrayList<Movie> mMovies = new ArrayList<>();
    private PosterRecyclerAdapter mPosterRecyclerAdapter;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray("Movies", mMovies);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_grid);
        initializeRecyclerView();
        if ( savedInstanceState != null){
            mMovies = (Movie[]) savedInstanceState.getParcelableArray("Movies");
            mPosterRecyclerAdapter = new PosterRecyclerAdapter(mMovies, this);
            mRecyclerView.setAdapter(mPosterRecyclerAdapter);
        }else{
            queryTheMovieDatabase(1);

        }

    }


    public void initializeRecyclerView (){
        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(this,342);
        mRecyclerView.setLayoutManager(autoFitGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
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
                    mPosterRecyclerAdapter = new PosterRecyclerAdapter(mMovies, MainActivity.this);
                    mRecyclerView.setAdapter(mPosterRecyclerAdapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
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
        if (itemClicked == R.id.sort_popular){
            //sort by popularity.desc
            queryTheMovieDatabase(R.id.sort_popular);
            return true;

        }else if (itemClicked == R.id.sort_rating){
            //use sort by vote_average.desc
            queryTheMovieDatabase(R.id.sort_rating);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
