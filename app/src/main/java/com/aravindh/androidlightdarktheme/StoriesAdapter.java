package com.aravindh.androidlightdarktheme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by razibkani on 3/18/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Stories> storiesList;
    private OnItemClickListener onItemClickListener;

    StoriesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        storiesList = new ArrayList<>();
    }

    public void setNewsList(ArrayList<Stories> storiesArrayList) {
        this.storiesList = storiesArrayList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.stories_adapter, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Stories stories = storiesList.get(position);

        ((ItemHolder) holder).title.setText(stories.getStoryName());
        ((ItemHolder) holder).description.setText(stories.getStoryDescription());
        ((ItemHolder) holder).date.setAlpha(0.5f);
        ((ItemHolder) holder).date.setText(stories.getStoryDate());

        if (onItemClickListener != null) {
            ((ItemHolder) holder).bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView date;
        ImageView bookmark;

        public ItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            description = (TextView) itemView.findViewById(R.id.item_description);
            date = (TextView) itemView.findViewById(R.id.item_date);
            bookmark = (ImageView) itemView.findViewById(R.id.item_bookmark);
        }
    }

    interface OnItemClickListener {
        void onClick(View view, int position);
    }
}
