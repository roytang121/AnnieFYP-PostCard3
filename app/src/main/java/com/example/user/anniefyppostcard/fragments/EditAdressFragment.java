package com.example.user.anniefyppostcard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.anniefyppostcard.R;

/**
 * Created by user on 15/3/2016.
 */
public class EditAdressFragment extends BaseFragment {
    private TextView inputView;
    public static EditMessageFragment newInstance() {
        EditMessageFragment f = new EditMessageFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_address, container, false);
        inputView = (TextView) root.findViewById(R.id.inputTextView);
        return root;
    }
}
