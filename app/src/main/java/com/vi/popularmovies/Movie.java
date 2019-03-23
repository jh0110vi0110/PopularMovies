package com.vi.popularmovies;
/*
{"page":1,"total_results":19853,"total_pages":993,"results":[
{
"vote_count":1176,
"id":399579,
"video":false,
"vote_average":6.8,
"title":"Alita: Battle Angel",
"popularity":334.096,
"poster_path":"\/xRWht48C2V8XNfzvPehyClOvDni.jpg",
"original_language":"en",
"original_title":"Alita: Battle Angel",
"genre_ids":[28,878,53],
"backdrop_path":"\/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
"adult":false,
"overview":"When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
"release_date":"2019-01-31"
},
{
"vote_count":31,
"id":450001,
"video":false,
"vote_average":5.8,
"title":"Master Z: Ip Man Legacy",
"popularity":93.255,
"poster_path":"\/f2PN2ff0VwcVUSgeJUx6pKHwp4r.jpg",
"original_language":"cn",
"original_title":"葉問外傳：張天志",
"genre_ids":[28],
"backdrop_path":"\/nv4KsjnhcSIZtuw2mkT9IxoQ5oq.jpg",
"adult":false,
"overview":"After being defeated by Ip Man, Cheung Tin Chi is attempting to keep a low profile. While going about his business, he gets into a fight with a foreigner by the name of Davidson, who is a big boss behind the bar district. Tin Chi fights hard with Wing Chun and earns respect.",
"release_date":"2018-12-20"
}
]
}
 */


import android.os.Parcel;
import android.os.Parcelable;
//public class Movie {
public class Movie implements Parcelable {
    // original_title (String)
    private String mOriginalTitle;
    //title (Localized) (String)
    private String mTitle;
    // poster_path (String)
    private String mPosterUrl;
    //backdrop_path
    private String mBackdropUrl;
    //plot_overview (String)
    private String mOverview;
    //vote_average
    private String mUserRating;
    //release_date
    private String mReleaseDate;
    //vote_count
    private String mVoteCount;
/*
    public Movie (){

    }
*/
    private Movie(Parcel in){
        this.mOriginalTitle = in.readString();
        this.mTitle = in.readString();
        this.mPosterUrl = in.readString();
        this.mBackdropUrl = in.readString();
        this.mOverview = in.readString();
        this.mUserRating = in.readString();
        this.mReleaseDate = in.readString();
        this.mVoteCount = in.readString();
    }


    public Movie(String mOriginalTitle, String mTitle, String mPosterUrl, String mBackdropUrl, String mOverview, String mUserRating, String mReleaseDate, String mVoteCount) {
        this.mOriginalTitle = mOriginalTitle;
        this.mTitle = mTitle;
        this.mPosterUrl = mPosterUrl;
        this.mBackdropUrl = mBackdropUrl;
        this.mOverview = mOverview;
        this.mUserRating = mUserRating;
        this.mReleaseDate = mReleaseDate;
        this.mVoteCount = mVoteCount;
    }

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
