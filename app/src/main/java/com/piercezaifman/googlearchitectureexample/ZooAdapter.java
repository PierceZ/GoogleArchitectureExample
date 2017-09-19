package com.piercezaifman.googlearchitectureexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class ZooAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Zoo> mZoos = new ArrayList<>();

    public void update(List<Zoo> zoos) {
        mZoos.clear();
        mZoos.addAll(zoos);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZooViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_zoo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZooViewHolder zooViewHolder = (ZooViewHolder) holder;
        Zoo zoo = mZoos.get(position);
        zooViewHolder.mNameTextView.setText(zoo.getName());
    }

    @Override
    public int getItemCount() {
        return mZoos.size();
    }


    private static class ZooViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;

        public ZooViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.row_zoo_textview_name);
        }
    }
}
