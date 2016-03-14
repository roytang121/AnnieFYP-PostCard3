package com.example.user.anniefyppostcard.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.anniefyppostcard.R;
import com.kbeanie.imagechooser.api.ChosenImage;

/**
 * Created by roytang on 13/3/2016.
 */
@Deprecated
public class StyleFragment extends BaseFragment {

    ImageView image;

    public static StyleFragment newInstance(ChosenImage image) {
        StyleFragment f = new StyleFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_photo, container, false);
        setup(root);
        return root;
    }

    private void setup(View root) {
        image = (ImageView) root.findViewById(R.id.image1);

        // set image with controller
        PostCardController controller = PostCardController.sharedInstance();
        ChosenImage chosenImage1 = controller.getChosenImage();

        // image path
        String image1Path = chosenImage1.getFileThumbnail();
        this.image.setImageURI(Uri.parse(image1Path));
    }
}
