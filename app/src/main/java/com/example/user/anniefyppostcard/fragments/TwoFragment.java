package com.example.user.anniefyppostcard.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.user.anniefyppostcard.Dp;
import com.example.user.anniefyppostcard.PostCardControllerDelegate;
import com.example.user.anniefyppostcard.PostCardView;
import com.example.user.anniefyppostcard.R;
import com.example.user.anniefyppostcard.activity.DesignPostCardsActivity;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class TwoFragment extends Fragment implements PostCardControllerDelegate {


    PhotoView photoView;
    PhotoViewAttacher mAttacher;
    PostCardView postCardView;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PostCardController.sharedInstance().addObserverDelegate(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit, container, false);

        /* set up photoview and attacher */
        photoView = (PhotoView) root.findViewById(R.id.photoView);
        photoView.setBackgroundColor(Color.parseColor("#333333"));

        postCardView = (PostCardView) root.findViewById(R.id.postcardView);

        Button nextButton = (Button) root.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNext(v);
            }
        });

        updateImage();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* fix photoview ratio to 4:3 */
        photoView.post(new Runnable() {
            @Override
            public void run() {
//                Point size = new Point();
//                getActivity().getWindowManager().getDefaultDisplay().getSize(size);
                int rootWidth = photoView.getWidth();

                final int computedHeight = (int) (rootWidth * 0.75);
                final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, computedHeight);
                final int margin = (int) Dp.toPx(9, getActivity().getApplicationContext());
                params.setMargins(margin, margin, margin, margin);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                photoView.setLayoutParams(params);
                photoView.setZoomable(true);

                postCardView.post(new Runnable() {
                    @Override
                    public void run() {
                        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, computedHeight + margin * 2);
                        postCardView.setLayoutParams(params);
                    }
                });
            }
        });

    }

    public void onNext(View view) {

        // save cropped image
//        photoView.get

        if (getActivity() instanceof DesignPostCardsActivity) {
            ((DesignPostCardsActivity) getActivity()).stage(2);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mAttacher.cleanup();

        PostCardController.sharedInstance().removeObserverDelegate(this);
    }

    private void updateImage() {
        photoView.setImageURI(PostCardController.sharedInstance().getChosenImageOriginUri());

        if (mAttacher == null) {
            mAttacher = new PhotoViewAttacher(photoView);
        } else {
            mAttacher.update();
        }
    }

    /* PostCardControllerDelegate */
    @Override
    public void onMediaUpdate() {
        updateImage();
    }
}
