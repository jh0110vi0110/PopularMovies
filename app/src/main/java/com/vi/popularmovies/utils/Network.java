package com.vi.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Network {
    static final String API_KEY = "184f4b1cac9ad7b41ddbd2fedc234199";
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_ERROR_500PX_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Attenzione_architetto_fr_01.svg/500px-Attenzione_architetto_fr_01.svg.png";
    public static final String IMG_SIZE = "w500";

    public static URL buildDataUrl(int sortType) {
        final String BASE_DATA_URL = "http://api.themoviedb.org/3/discover/movie";
        final String P_API_KEY = "api_key";
        final String P_SORT = "sort_by";

        String sortChoice = "popularity.desc";
        /*
        String sortChoice = SORT_POPULAR;
        if (sortType == R.id.sort_rating){
            sortChoice = SORT_RATING;
        }
        */
        Uri builtUri = Uri.parse(BASE_DATA_URL).buildUpon()
                .appendQueryParameter(P_API_KEY, API_KEY)
                .appendQueryParameter(P_SORT, sortChoice)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
            Log.d("NetworkUtil", "buildUrl: " + url.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
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
