package com.vi.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vi.popularmovies.utils.Network;

import java.util.ArrayList;

public class PosterRecyclerAdapter extends RecyclerView.Adapter<PosterRecyclerAdapter.PosterViewHolder> {
    private static final String TAG = "PosterRecyclerAdapter";
    //private ArrayList<Movie> mMovies = new ArrayList<>();
    private Movie[] mMovies;
    //public PosterRecyclerAdapter (ArrayList<Movie> movies) {
    public PosterRecyclerAdapter (Movie[] movies) {
        this.mMovies = movies;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_list_item, viewGroup, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder posterViewHolder, int i) {
        String posterPath = null;
        if (mMovies[i].getmPosterUrl() == "null"){
            posterPath = Network.IMAGE_ERROR_500PX_URL;
        }else{
            posterPath = Network.BASE_IMAGE_URL + Network.IMG_SIZE + mMovies[i].getmPosterUrl();
        }
        Picasso.get()
                //.load(url.toString())
                .load(posterPath)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                //.resize(700, 700)
                //.centerInside()
                //.centerCrop()
                .into(posterViewHolder.posterImage);

        //posterViewHolder.posterTitle.setText(mMovies.get(i).getmTitle());
        posterViewHolder.posterTitle.setText(mMovies[i].getmTitle());


    }

    @Override
    public int getItemCount() {
        //return mMovies.size();
        if ( mMovies == null){
            return 0;
        }
        return mMovies.length;
    }

    //View Holder Class
    public class PosterViewHolder extends RecyclerView.ViewHolder{
        ImageView posterImage;
        TextView posterTitle;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.iv_poster_image);
            posterTitle = itemView.findViewById(R.id.tv_poster_name);
        }
    }
    
}
