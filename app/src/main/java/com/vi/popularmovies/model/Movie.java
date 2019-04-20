package com.vi.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {

    private String mId;
    private String mOriginalTitle;
    private String mTitle;
    private String mPosterUrl;
    private String mBackdropUrl;
    private String mOverview;
    private String mUserRating;
    private String mReleaseDate;
    private String mVoteCount;

    private Movie(Parcel in){
        this.mId = in.readString();
        this.mOriginalTitle = in.readString();
        this.mTitle = in.readString();
        this.mPosterUrl = in.readString();
        this.mBackdropUrl = in.readString();
        this.mOverview = in.readString();
        this.mUserRating = in.readString();
        this.mReleaseDate = in.readString();
        this.mVoteCount = in.readString();
    }

    public Movie( String mId, String mOriginalTitle, String mTitle, String mPosterUrl, String mBackdropUrl, String mOverview, String mUserRating, String mReleaseDate, String mVoteCount) {
        this.mId = mId;
        this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mPosterUrl = mPosterUrl;
        this.mBackdropUrl = mBackdropUrl;
        this.mOverview = mOverview;
        this.mUserRating = mUserRating;
        this.mReleaseDate = mReleaseDate;
        this.mVoteCount = mVoteCount;
    }

    public String getmId() { return mId; }

    public void setmId(String mId) { this.mId = mId; }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPosterUrl() {
        return mPosterUrl;
    }

    public void setmPosterUrl(String mPosterUrl) {
        this.mPosterUrl = mPosterUrl;
    }

    public String getmBackdropUrl() {
        return mBackdropUrl;
    }

    public void setmBackdropUrl(String mBackdropUrl) {
        this.mBackdropUrl = mBackdropUrl;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmUserRating() {
        return mUserRating;
    }

    public void setmUserRating(String mUserRating) {
        this.mUserRating = mUserRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(String mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // done TODO add Id and other data here
        dest.writeString(mId);
        dest.writeString(mOriginalTitle);
        dest.writeString(mTitle);
        dest.writeString(mPosterUrl);
        dest.writeString(mBackdropUrl);
        dest.writeString(mOverview);
        dest.writeString(mUserRating);
        dest.writeString(mReleaseDate);
        dest.writeString(mVoteCount);
    }

    static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>(){

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
