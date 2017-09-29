package com.piercezaifman.googlearchitectureexample.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.piercezaifman.googlearchitectureexample.R;
import com.piercezaifman.googlearchitectureexample.model.Animal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierce Zaifman on 2017-09-29.
 */

public class AnimalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Animal> mAnimals = new ArrayList<>();

    public void update(List<Animal> animals) {
        mAnimals.clear();
        mAnimals.addAll(animals);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animal, parent, false);
        return new AnimalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AnimalViewHolder animalViewHolder = (AnimalViewHolder) holder;
        Animal animal = mAnimals.get(position);
        animalViewHolder.mGroupTextView.setText(animal.getGroup());
        animalViewHolder.mNameTextView.setText(animal.getName());

        Context context = animalViewHolder.mGroupTextView.getContext();
        Picasso.with(context).load(animal.getImage()).fit().into(animalViewHolder.mAnimalImageView);
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    private static class AnimalViewHolder extends RecyclerView.ViewHolder {

        TextView mGroupTextView;
        TextView mNameTextView;
        ImageView mAnimalImageView;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            mGroupTextView = itemView.findViewById(R.id.row_animal_textview_group);
            mNameTextView = itemView.findViewById(R.id.row_animal_textview_name);
            mAnimalImageView = itemView.findViewById(R.id.row_animal_imageview);
        }
    }
}
