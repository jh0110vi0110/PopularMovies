package com.vi.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.vi.popularmovies.utils.Json;
import com.vi.popularmovies.utils.Network;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Movie[] mMovies;
    //private ArrayList<Movie> mMovies = new ArrayList<>();
    private PosterRecyclerAdapter mPosterRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_grid);

        initializeRecyclerView();
        queryTheMovieDatabase(1);
    }


    public void initializeRecyclerView (){
        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(this,500);
        mRecyclerView.setLayoutManager(autoFitGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
       // mPosterRecyclerAdapter = new PosterRecyclerAdapter(mMovies);
        //mRecyclerView.setAdapter(mPosterRecyclerAdapter);
    }

    public void queryTheMovieDatabase(int sortType){

        URL dataUrl = Network.buildDataUrl(sortType);
        new QueryMovieDatabase().execute(dataUrl);
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
            //super.onPostExecute(s);
            if ( result != null && result != ""){
                try {
                    mMovies = Json.readMoviesJson(result);
                    mPosterRecyclerAdapter = new PosterRecyclerAdapter(mMovies);
                    mRecyclerView.setAdapter(mPosterRecyclerAdapter);
                    //mRecyclerView.setAdapter(mPosterRecyclerAdapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
