package com.vi.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vi.popularmovies.model.Movie;
import com.vi.popularmovies.utils.Network;

public class PosterRecyclerAdapter extends RecyclerView.Adapter<PosterRecyclerAdapter.PosterViewHolder> {
    private static final String TAG = "PosterRecyclerAdapter";
    private OnPosterListener mOnPosterListener;
    private Movie[] mMovies;

    public PosterRecyclerAdapter (Movie[] movies, OnPosterListener onPosterListener) {
        this.mMovies = movies;
        this.mOnPosterListener = onPosterListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_list_item, viewGroup, false);
        return new PosterViewHolder(view, mOnPosterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder posterViewHolder, int i) {
        //Set Poster In grid
        Picasso.get()
                .load(Network.createImageUrlString(mMovies[i].getmPosterUrl()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(posterViewHolder.posterImage);

        posterViewHolder.posterTitle.setText(mMovies[i].getmTitle());
    }

    @Override
    public int getItemCount() {
        if ( mMovies == null){
            return 0;
        }
        return mMovies.length;
    }

    //View Holder Class
    public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImage;
        TextView posterTitle;
        OnPosterListener onPosterListener;

        public PosterViewHolder(@NonNull View itemView, OnPosterListener onPosterListener) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.iv_poster_image);
            posterTitle = itemView.findViewById(R.id.tv_poster_name);
            this.onPosterListener = onPosterListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPosterListener.onPosterClick(getAdapterPosition());
        }
    }

    public interface OnPosterListener {
        void onPosterClick (int position);
    }
    
}
