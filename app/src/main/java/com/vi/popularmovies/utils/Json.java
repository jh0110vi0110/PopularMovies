package com.vi.popularmovies.utils;

import android.util.Log;

import com.vi.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class Json {

    //JSON Keys
    // id
    private final static String ID = "id";
    // results
    private final static String RESULTS = "results";
    // original_title
    private final static String ORIGINAL_TITLE = "original_title";
    //title (Localized) (String)
    private final static String TITLE = "title";
    // poster_path (String)
    private final static String POSTER_PATH = "poster_path";
    //backdrop_path
    private final static String BACKDROP_PATH = "backdrop_path";
    //plot_overview (String)
    private final static String OVERVIEW = "overview";
    //vote_average
    private final static String VOTE_AVERAGE = "vote_average";
    //release_date
    private final static String RELEASE_DATE = "release_date";
    //vote_count
    private final static String VOTE_COUNT = "vote_count";

    public static Movie[] readMoviesJson (String jsonString) throws JSONException {
        JSONObject fullResult = new JSONObject(jsonString);
        JSONArray movieResults = fullResult.getJSONArray(RESULTS);
        Movie[] movieData = new Movie[movieResults.length()];

        for (int i = 0; i < movieResults.length(); i++){
            Movie movie = new Movie(
                    movieResults.getJSONObject(i).optString(ID),
                    movieResults.getJSONObject(i).optString(ORIGINAL_TITLE),
                    movieResults.getJSONObject(i).optString(TITLE),
                    movieResults.getJSONObject(i).optString(POSTER_PATH),
                    movieResults.getJSONObject(i).optString(BACKDROP_PATH),
                    movieResults.getJSONObject(i).optString(OVERVIEW),
                    movieResults.getJSONObject(i).optString(VOTE_AVERAGE),
                    movieResults.getJSONObject(i).optString(RELEASE_DATE),
                    movieResults.getJSONObject(i).optString(VOTE_COUNT)
            );

            movieData[i] = movie;
            Log.d(TAG, "readMoviesJson: ID Received: " + movieData[i].getmTitle() + " " + movieData[i].getmId() );

        }

        return movieData;
    }
}
