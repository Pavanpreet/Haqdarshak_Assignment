package com.example.haqdarshak_assignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

class NewsAdapter extends ArrayAdapter<DataNews> {
    NewsAdapter(@NonNull Context context, @NonNull List<DataNews> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.sample_news_list_view, parent, false);

        }

        DataNews currentItem = getItem(position);

        //Assigning News Type..
        TextView newsType = listItemView.findViewById(R.id.newsType);
        assert currentItem != null;
        newsType.setText(currentItem.getNewsType());

        //TODO: Implement Glide library to display images.
        //Assigning News Image in Glide..
        ImageView newsImg = listItemView.findViewById(R.id.newsImg);
        boolean isPhoto = currentItem.getNewsImg() != null;
        if (isPhoto) {  //if photo is available.
            Glide.with(newsImg.getContext())
                    .load(currentItem.getNewsImg())
                    .into(newsImg);
        } else {
            Log.e("Image Error", "Cannot Load Image");
        }

        // TODO: Also have to call all the references here.

        TextView newsTitle = listItemView.findViewById(R.id.newsTitle);
        newsTitle.setText(currentItem.getNewsTitle());

        TextView newsDesc = listItemView.findViewById(R.id.newsDesc);
        newsDesc.setText(currentItem.getNewsDesc());

        TextView newsAuthor = listItemView.findViewById(R.id.newsAuthor);
        newsAuthor.setText(currentItem.getNewsAuthor());

        TextView newsDate = listItemView.findViewById(R.id.newsDate);
        newsDate.setText(currentItem.getNewsDate());

        return listItemView;
    }

}

