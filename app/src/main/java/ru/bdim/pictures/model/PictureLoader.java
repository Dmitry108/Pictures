package ru.bdim.pictures.model;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PictureLoader {
    public static void setPicture(String url, ImageView img){
        Picasso.with(img.getContext()).load(url).into(img);
    }
}