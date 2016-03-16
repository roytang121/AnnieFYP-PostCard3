package com.example.user.anniefyppostcard.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.anniefyppostcard.PostCardView;
import com.example.user.anniefyppostcard.R;
import com.example.user.anniefyppostcard.activity.DesignPostCardsActivity;
import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;


public class AddPhotoFragment extends BaseFragment implements View.OnClickListener, ImageChooserListener {

    private PostCardView postCardView;

    private String[] pickerOptions = new String[]{
            "Photo album",
            "Library"
    };
    private int chooserType;
    private ImageChooserManager imageChooserManager;
    private String mediaPath;
    private String finalPath;
    private String thumbPath;
    private String thumbPathSmall;

    public AddPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_photo, container, false);
        setup(root);

        return root;
    }

    public void setup(View root) {
        postCardView = (PostCardView) root.findViewById(R.id.postcardView);

        postCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == this.postCardView) {
            // create dialog
            showPicker();
        }
    }

    private void showPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Select Photo");
        builder.setItems(pickerOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // album
                    chooseImage();

                } else if (which == 1) {
                    // library
                }
            }
        });
        builder.show();
    }

    /* Image chooser */
    public void chooseImage() {

        int permissionCheck1 = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int permissionCheck2 = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck1 != PackageManager.PERMISSION_GRANTED || permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }


        chooserType = ChooserType.REQUEST_PICK_PICTURE;
        imageChooserManager = new ImageChooserManager(this,
                ChooserType.REQUEST_PICK_PICTURE, true);
        imageChooserManager.setImageChooserListener(this);
        try {
            mediaPath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ChooserType.REQUEST_PICK_PICTURE) {
                if (imageChooserManager == null) {
                    imageChooserManager = new ImageChooserManager(this, requestCode, true);
                    imageChooserManager.setImageChooserListener(this);
                    imageChooserManager.reinitialize(mediaPath);
                }
                imageChooserManager.submit(requestCode, data);
            } else if (requestCode == 101) { // permission request
                this.chooseImage();
            }
        }
    }

    /* ImageChooserListener */
    @Override
    public void onImageChosen(final ChosenImage image) {

        // not in main thread
        finalPath = image.getFilePathOriginal();
        thumbPath = image.getFileThumbnail();
        thumbPathSmall = image.getFileThumbnailSmall();
        final Bitmap decodedOriginal = BitmapFactory.decodeFile(image.getFilePathOriginal());


        this.getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // should run on ui thread
                PostCardController.sharedInstance().setChosenImage(image);

                if (image != null) {
                    if (getActivity() instanceof DesignPostCardsActivity) {
                        ((DesignPostCardsActivity) getActivity()).stage(1);
                    }
                }

                // set to postcardView
                if (decodedOriginal != null) {
                    postCardView.setBitmap(decodedOriginal);
                }
            }
        });
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onImagesChosen(ChosenImages chosenImages) {

    }
}
