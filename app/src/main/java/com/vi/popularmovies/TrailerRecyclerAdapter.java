package com.vi.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vi.popularmovies.model.MovieTrailer;

public class TrailerRecyclerAdapter extends RecyclerView.Adapter<TrailerRecyclerAdapter.TrailerViewHolder> {

    private OnTrailerListener mOnTrailerListener;
    private MovieTrailer[] mMovieTrailers;

    public TrailerRecyclerAdapter(OnTrailerListener mOnTrailerListener, MovieTrailer[] mMovieTrailers) {
        this.mOnTrailerListener = mOnTrailerListener;
        this.mMovieTrailers = mMovieTrailers;
    }

    public void setMovieTrailersData(MovieTrailer[] mMovieTrailers) {
        this.mMovieTrailers = mMovieTrailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_list_item, viewGroup, false);
        return new TrailerViewHolder(view, mOnTrailerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, int i) {
        trailerViewHolder.trailerTextView.setText(mMovieTrailers[i].getName());
    }

    @Override
    public int getItemCount() {
        if (mMovieTrailers == null){
            return 0;
        }
        return mMovieTrailers.length;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnTrailerListener onTrailerListener;
        TextView trailerTextView;

        public TrailerViewHolder(@NonNull View itemView, OnTrailerListener onTrailerListener) {
            // TODO findviewbyID for textview
            super(itemView);
            this.onTrailerListener = onTrailerListener;
            trailerTextView = itemView.findViewById(R.id.tv_trailer_name);
            //this.trailerTextView = trailerTextView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { onTrailerListener.onTrailerClick(getAdapterPosition()); }
    }

    public interface OnTrailerListener {
        void onTrailerClick (int position);
    }
}
