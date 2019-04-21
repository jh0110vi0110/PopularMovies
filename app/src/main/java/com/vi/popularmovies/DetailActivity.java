package com.vi.popularmovies;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vi.popularmovies.model.Movie;
import com.vi.popularmovies.model.MovieTrailer;
import com.vi.popularmovies.utils.Network;

public class DetailActivity extends AppCompatActivity implements TrailerRecyclerAdapter.OnTrailerListener {
    private Movie mMovie;
    private MovieTrailer[] mMovieTrailers;
    private RecyclerView mTrailerRecyclerView;
    private TrailerRecyclerAdapter mTrailerAdapter;

    private ImageView mDetailImageView;
    private TextView mTitleTextView, mOverviewTextView, mVoteAverageTextView, mReleaseDateTextView, mVoteCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        displayMovieDetails();

        initializeTrailerRecyclerview();



    }

    public void displayMovieDetails(){
        mDetailImageView = (ImageView) findViewById(R.id.iv_detail_background);
        mTitleTextView = (TextView) findViewById(R.id.tv_detail_title);
        mOverviewTextView = (TextView) findViewById(R.id.tv_detail_overview);
        mVoteAverageTextView = (TextView) findViewById(R.id.tv_detail_vote_average);
        mReleaseDateTextView = (TextView) findViewById(R.id.tv_detail_release_date);
        mVoteCountTextView = (TextView) findViewById(R.id.tv_detail_vote_count);
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("Movie")) {
                mMovie = intentThatStartedThisActivity.getParcelableExtra("Movie");

                //Set Poster Image with Picasso
                Picasso.get()
                        .load(Network.createImageUrlString(mMovie.getmPosterUrl()))
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .resize(350, 350)
                        .centerCrop()
                        .into(mDetailImageView);

                //Set Text Views using the Extra
                mTitleTextView.setText(mMovie.getmTitle());
                mOverviewTextView.setText(mMovie.getmOverview());
                mVoteCountTextView.setText(getString(R.string.vote_count_default) + mMovie.getmVoteCount());
                mReleaseDateTextView.setText(getString(R.string.release_date_default) + mMovie.getmReleaseDate());
                mVoteAverageTextView.setText(getString(R.string.vote_average_default) + mMovie.getmUserRating());

                //Display additional titles below main title if they are different
                if (!mMovie.getmTitle().equals(mMovie.getmOriginalTitle())) {
                    mTitleTextView.append("\n" + "(" + mMovie.getmOriginalTitle() + ")");
                }
            }
        }

    }

    public void initializeTrailerRecyclerview(){
        mTrailerRecyclerView = findViewById(R.id.rv_detail_trailers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mTrailerRecyclerView.setLayoutManager(linearLayoutManager);
        mTrailerRecyclerView.setHasFixedSize(true);
        mTrailerAdapter = new TrailerRecyclerAdapter(this, mMovieTrailers);
        mTrailerRecyclerView.setAdapter(mTrailerAdapter);
    }

    @Override
    public void onTrailerClick(int position) {
        MovieTrailer trailerClicked = mMovieTrailers[position];
        launchTrailerIntents(trailerClicked);
        // Fire intent to launch youtube

    }

    private void launchTrailerIntents(MovieTrailer movieTrailer){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + movieTrailer.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieTrailer.getUrl()));
        // what the heck
        //webIntent.putExtra("finish_on_ended", true);
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException e) {
            startActivity(webIntent);
        }
    }
}
