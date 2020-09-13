package com.example.onclicklistenerrecyclerviewjava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.GalleryViewHolder> {

    private Context mContext;
    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;

    RecyclerViewAdapter(Context mContext, ArrayList<String> mImageNames,
                        ArrayList<String> mImages) {
        this.mContext = mContext;
        this.mImageNames = mImageNames;
        this.mImages = mImages;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listitems,
                        parent,
                        false);
        GalleryViewHolder holder;
        holder = new GalleryViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.imageView);

        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new GalleryOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder{

        @SuppressWarnings("deprecation")
        CircleImageView imageView;
        TextView imageName;
        RelativeLayout parentLayout;

        GalleryViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.images);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

    private class GalleryOnClickListener implements View.OnClickListener{

        int position;

        GalleryOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(mContext, GalleryActivity.class);
            intent.putExtra("image_url", mImages.get(position));
            intent.putExtra("image_name", mImageNames.get(position));
            mContext.startActivity(intent);
        }
    }
}
