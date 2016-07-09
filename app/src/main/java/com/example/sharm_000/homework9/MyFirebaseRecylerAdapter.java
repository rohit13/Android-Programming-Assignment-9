package com.example.sharm_000.homework9;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

public class MyFirebaseRecylerAdapter extends FirebaseRecyclerAdapter<Movie, MyFirebaseRecylerAdapter.MovieViewHolder> {

    private Context mContext;
    private static OnItemClickListener mItemClickListener;
    public MyFirebaseRecylerAdapter(Class<Movie> modelClass, int modelLayout,
                                    Class<MovieViewHolder> holder, Query ref, Context context) {
        super(modelClass, modelLayout, holder, ref);
        this.mContext = context;
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
        public void onOverFlowButtonClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    protected void populateViewHolder(MovieViewHolder movieViewHolder, Movie movie, int i) {

        //TODO: Populate viewHolder by setting the movie attributes to cardview fields
        movieViewHolder.movieName.setText(movie.getName());
        movieViewHolder.movieDesc.setText(movie.getDescription());
        Picasso.with(mContext).load(movie.getUrl()).into(movieViewHolder.movieImage);
        movieViewHolder.rating.setText(movie.getRating()+"/10");

    }

    //TODO: Populate ViewHolder and add listeners.
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView movieName;
        public TextView movieDesc;
        public TextView rating;
        public ImageView overflow;

        public MovieViewHolder(View v) {
            super(v);
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
            movieDesc = (TextView) itemView.findViewById(R.id.movieDesc);
            rating = (TextView) itemView.findViewById(R.id.rating);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(mItemClickListener!=null)
                        mItemClickListener.onItemClick(v,getPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if(mItemClickListener!=null)
                        mItemClickListener.onItemLongClick(v,getPosition());
                    return true;
                }
            });

            if(overflow!=null){
                overflow.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(mItemClickListener!=null)
                            mItemClickListener.onOverFlowButtonClick(v,getPosition());
                    }
                });
            }
        }

    }

}
