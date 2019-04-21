package com.vi.popularmovies.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.vi.popularmovies.model.Movie;

@Entity (tableName = "movieFavorite")
public class MovieFavorite {

    @PrimaryKey
    private int databaseId;

    //private String mId;
    private String originalTitle;
    private String title;
    private String posterUrl;
    private String backdropUrl;
    private String overview;
    private String userRating;
    private String releaseDate;
    private String voteCount;


    public MovieFavorite(int databaseId, String originalTitle, String title, String posterUrl, String backdropUrl, String overview, String userRating, String releaseDate, String voteCount) {
        this.databaseId = databaseId;
        this.originalTitle = originalTitle;
        this.title = title;
        this.posterUrl = posterUrl;
        this.backdropUrl = backdropUrl;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.voteCount = voteCount;
    }

    @Ignore
    public MovieFavorite (Movie movie){
        this.databaseId = Integer.valueOf(movie.getmId());
        this.originalTitle = movie.getmOriginalTitle();
        this.title = movie.getmTitle();
        this.posterUrl = movie.getmPosterUrl();
        this.backdropUrl = movie.getmBackdropUrl();
        this.overview = movie.getmOverview();
        this.userRating = movie.getmUserRating();
        this.releaseDate = movie.getmReleaseDate();
        this.voteCount = movie.getmVoteCount();


    }

    public int getDatabaseId() { return databaseId; }

    public void setDatabaseId(int databaseId) { this.databaseId = databaseId; }

    public String getOriginalTitle() { return originalTitle; }

    public void setOriginalTitle(String originalTitle) { this.originalTitle = originalTitle; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPosterUrl() { return posterUrl; }

    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public String getBackdropUrl() { return backdropUrl; }

    public void setBackdropUrl(String backdropUrl) { this.backdropUrl = backdropUrl; }

    public String getOverview() { return overview; }

    public void setOverview(String overview) { this.overview = overview; }

    public String getUserRating() { return userRating; }

    public void setUserRating(String userRating) { this.userRating = userRating; }

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getVoteCount() { return voteCount; }

    public void setVoteCount(String voteCount) { this.voteCount = voteCount; }
}
