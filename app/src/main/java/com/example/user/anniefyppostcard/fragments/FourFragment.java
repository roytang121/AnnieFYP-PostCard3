package com.example.user.anniefyppostcard.fragments;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.anniefyppostcard.PostCardView;
import com.example.user.anniefyppostcard.R;


/**
 * Created by user on 15/3/2016.
 */

public class FourFragment extends Fragment{
    private static final String LOCAL_FILE = "https://roytang121.github.io/jsvr-demo";

    private View rootView;

    private PostCardView postCardView;

    public FourFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ar, container, false);

        postCardView = (PostCardView) rootView.findViewById(R.id.postcardView);
        postCardView.setBitmap(BitmapFactory.decodeResource(this.getActivity().getResources(),R.drawable.default_ar));

        return rootView;
    }
}
