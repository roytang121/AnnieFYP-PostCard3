package com.example.user.anniefyppostcard.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.anniefyppostcard.PostCardControllerDelegate;
import com.example.user.anniefyppostcard.R;
import com.example.user.anniefyppostcard.activity.EditMessageActivity;


public class ThreeFragment extends Fragment implements PostCardControllerDelegate {

    TextView previewMessage;
    TextView previewAddress;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_write, container, false);

        PostCardController.sharedInstance().addObserverDelegate(this);

        previewMessage = (TextView) root.findViewById(R.id.previewMessage);
        previewAddress = (TextView) root.findViewById(R.id.previewAddress);

        View writeButton =  root.findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditMessageActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return root;
    }

    private void updateData() {
        final PostCardController controller = PostCardController.sharedInstance();
        if (controller.getMessage() != null) {
            this.previewMessage.post(new Runnable() {
                @Override
                public void run() {
                    previewMessage.setText(controller.getMessage().getText());
                }
            });

        }

        if (controller.getAddress() != null) {
            this.previewAddress.setText(controller.getAddress().getLine1());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        PostCardController.sharedInstance().removeObserverDelegate(this);
    }

    // PostCardControllerDelegate

    @Override
    public void onMediaUpdate() {

    }

    @Override
    public void onDataUpdate() {
        updateData();
    }
}
