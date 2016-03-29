package com.example.user.anniefyppostcard.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.anniefyppostcard.PostCardView;
import com.example.user.anniefyppostcard.R;


/**
 * Created by user on 15/3/2016.
 */
public class FiveFragment extends Fragment {
    public FiveFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send, container, false);
        PostCardView postCardView = (PostCardView) rootView.findViewById(R.id.postcardView);
        postCardView.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_ar));

        Button button1 = (Button) rootView.findViewById(R.id.frameButton);
        Button button2 = (Button) rootView.findViewById(R.id.layoutButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

        return rootView;
    }

    private void showAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle("In-App Purchase")
                .setMessage("Confirm payment?")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}