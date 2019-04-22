package com.vi.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import com.vi.popularmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Network {
    private static final String TAG = "util.Network";
    private static final String P_API_KEY = "api_key";
    private static final String API_KEY = "API KEY GOES HERE";
    private static final String BASE_DATA_URL = "https://api.themoviedb.org/3/movie";

    public static URL buildDataUrl(int sortType) {

        String queryType = "popular";
        if (sortType == R.id.sort_rating){
            queryType = "top_rated";
        }
        Uri builtUri = Uri.parse(BASE_DATA_URL).buildUpon()
                .appendPath(queryType)
                .appendQueryParameter(P_API_KEY, API_KEY)
                .build();
        return getUrl(builtUri);
    }

    public static URL buildDetailDataUrl (String movieId, String urlType){
        //urlType "videos" or "reviews"
        if ( movieId == null || urlType == null ){
            return null;
        }
        Uri builtUri = Uri.parse(BASE_DATA_URL).buildUpon()
                .appendPath(movieId)
                .appendPath(urlType)
                .appendQueryParameter(P_API_KEY, API_KEY)
                .build();
        return getUrl(builtUri);

    }

    public static URL buildYoutubeUrl (String key){
        // https://www.youtube.com/watch?v=4vPeTSRd580
        if (key == null || key.equals("")){
            return null;
        }
        final String BASE_YOUTUBE_URL = "https://www.youtube.com/watch";
        final String P_VIDEO_KEY = "v";
        Uri builtUri = Uri.parse(BASE_YOUTUBE_URL).buildUpon()
                .appendQueryParameter(P_VIDEO_KEY, key)
                .build();
        return getUrl(builtUri);

    }

    private static URL getUrl(Uri builtUri) {
        URL url = null;
        try {
            url = new URL(builtUri.toString());
            Log.d(TAG, "buildDataUrl: " + url.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createImageUrlString(String path){
        final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
        final String IMAGE_ERROR_500PX_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Attenzione_architetto_fr_01.svg/500px-Attenzione_architetto_fr_01.svg.png";
        // size options...w125, w342, w500
        final String IMG_SIZE = "w342";

        if (path == "null"){
            return IMAGE_ERROR_500PX_URL;
        }else{
            return BASE_IMAGE_URL + IMG_SIZE + path;
        }

    }

    // Taken from Exercise 2
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
