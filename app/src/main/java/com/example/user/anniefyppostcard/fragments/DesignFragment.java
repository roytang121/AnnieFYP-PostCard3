package com.example.user.anniefyppostcard.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.user.anniefyppostcard.R;
import com.example.user.anniefyppostcard.activity.DesignPostCardsActivity;

/**
 * Created by roytang on 13/3/2016.
 */
public class DesignFragment extends BaseFragment {
    public static DesignFragment newInstance() {
        return new DesignFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_design, container, false);

        ImageButton button = (ImageButton) root.findViewById(R.id.design_image_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump to new activity
                Activity a = getActivity();
                if (a != null) {
                    Intent intent = new Intent(a, DesignPostCardsActivity.class);
                    a.startActivity(intent);
                }
            }
        });
        return root;
    }
}
