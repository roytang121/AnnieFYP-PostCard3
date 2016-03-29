package com.example.user.anniefyppostcard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.anniefyppostcard.R;

/**
 * Created by roytang on 29/3/2016.
 */
public class SinglePicFragment extends Fragment{
    public static SinglePicFragment newInstance(int resource) {
        SinglePicFragment f = new SinglePicFragment();
        f.resource = resource;
        return f;
    }

    private int resource = R.drawable.greece;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_pic, container, false);
        ImageView img = (ImageView) rootView.findViewById(R.id.img);
        img.setImageResource(this.resource);
        return rootView;
    }
}
