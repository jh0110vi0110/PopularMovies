package com.vi.popularmovies.utils;

import android.util.Log;

import com.vi.popularmovies.model.Movie;
import com.vi.popularmovies.model.MovieReview;
import com.vi.popularmovies.model.MovieTrailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class Json {

    //JSON Keys
    // error String
    private final static String NOT_FOUND = "Not Found";

    // common tags
    private final static String ID = "id";
    private final static String RESULTS = "results";

    // movie tags
    private final static String ORIGINAL_TITLE = "original_title";
    private final static String TITLE = "title";
    private final static String POSTER_PATH = "poster_path";
    private final static String BACKDROP_PATH = "backdrop_path";
    private final static String OVERVIEW = "overview";
    private final static String VOTE_AVERAGE = "vote_average";
    private final static String RELEASE_DATE = "release_date";
    private final static String VOTE_COUNT = "vote_count";

    //trailer keys
    private final static String NAME = "name";
    private final static String LANGUAGE = "iso_639_1";
    private final static String COUNTRY = "iso_3166_1";
    private final static String KEY = "key";
    private final static String SITE = "site";
    private final static String SIZE = "size";
    private final static String TYPE = "type";

    // review keys
    private final static String AUTHOR = "author";
    private final static String CONTENT = "content";
    private final static String URL = "url";

    public static Movie[] readMoviesJson (String jsonString) throws JSONException {
        JSONObject fullResult = new JSONObject(jsonString);
        JSONArray movieResults = fullResult.optJSONArray(RESULTS);
        Movie[] movieData = new Movie[movieResults.length()];

        for (int i = 0; i < movieResults.length(); i++){
            Movie movie = new Movie(
                    movieResults.getJSONObject(i).optString(ID, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(ORIGINAL_TITLE, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(TITLE, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(POSTER_PATH, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(BACKDROP_PATH, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(OVERVIEW, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(VOTE_AVERAGE, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(RELEASE_DATE, NOT_FOUND),
                    movieResults.getJSONObject(i).optString(VOTE_COUNT, NOT_FOUND)
            );
            movieData[i] = movie;
            //Log.d(TAG, "readMoviesJson: ID Received: " + movieData[i].getmTitle() + " " + movieData[i].getmId() );
        }
        return movieData;
    }

    public static MovieTrailer[] readTrailersJson (String jsonString) throws JSONException{
        JSONObject fullResult = new JSONObject(jsonString);
        JSONArray trailerResults = fullResult.optJSONArray(RESULTS);
        MovieTrailer[] movieTrailers = new MovieTrailer[trailerResults.length()];

        for (int i = 0; i < trailerResults.length(); i++){
            MovieTrailer trailer = new MovieTrailer(
                    trailerResults.getJSONObject(i).optString(ID, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(NAME, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(LANGUAGE, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(COUNTRY, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(KEY, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(SITE, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(SIZE, NOT_FOUND),
                    trailerResults.getJSONObject(i).optString(TYPE, NOT_FOUND)
            );
            movieTrailers[i] = trailer;
        }
        return movieTrailers;
    }

    public static MovieReview[] readReviewsJson (String jsonString) throws JSONException{
        JSONObject fullResult = new JSONObject(jsonString);
        JSONArray reviewResults = fullResult.optJSONArray(RESULTS);
        MovieReview[] movieReviews = new MovieReview[reviewResults.length()];

        for (int i = 0; i < reviewResults.length(); i++){
            MovieReview review = new MovieReview(
                    reviewResults.getJSONObject(i).optString(ID, NOT_FOUND),
                    reviewResults.getJSONObject(i).optString(AUTHOR, NOT_FOUND),
                    reviewResults.getJSONObject(i).optString(CONTENT, NOT_FOUND),
                    reviewResults.getJSONObject(i).optString(URL, NOT_FOUND)
            );
            movieReviews[i] = review;
        }
        return movieReviews;
    }
}
