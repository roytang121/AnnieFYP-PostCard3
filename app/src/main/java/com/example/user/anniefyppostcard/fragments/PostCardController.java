package com.example.user.anniefyppostcard.fragments;

import android.net.Uri;

import com.example.user.anniefyppostcard.PostCardAddress;
import com.example.user.anniefyppostcard.PostCardControllerDelegate;
import com.example.user.anniefyppostcard.PostCardMessage;
import com.kbeanie.imagechooser.api.ChosenImage;

import java.util.ArrayList;

/**
 * Created by roytang on 13/3/2016.
 */
public class PostCardController {
    private static PostCardController mInstance;

    private ArrayList<PostCardControllerDelegate> delegates;

    /* fields */
    private ChosenImage chosenImage;

    private PostCardMessage message;

    private PostCardAddress address;
//    private

    public static PostCardController sharedInstance() {
        if (mInstance == null) {
            mInstance = new PostCardController();
            mInstance.delegates = new ArrayList<>();
        }
        return mInstance;
    }

    public PostCardController() {
    }

    public ChosenImage getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(ChosenImage chosenImage) {
        this.chosenImage = chosenImage;

        // update delegates
        for (PostCardControllerDelegate delegate : this.delegates) {
            delegate.onMediaUpdate();
        }
    }

    public Uri getChosenImageThumbnailUri() {
        if (this.chosenImage != null) {
            return Uri.parse(this.getChosenImage().getFileThumbnail());
        } else {
            return null;
        }
    }

    public Uri getChosenImageOriginUri() {
        if (this.chosenImage != null) {
            return Uri.parse(this.getChosenImage().getFilePathOriginal());
        } else {
            return null;
        }
    }

    public boolean isImageChosen() {
        return this.chosenImage != null;
    }

    public void addObserverDelegate(PostCardControllerDelegate delegate) {
        if (!delegates.contains(delegate)) {
            this.delegates.add(delegate);
        }
    }

    public void removeObserverDelegate(PostCardControllerDelegate object) {
        this.delegates.remove(object);
    }



    public PostCardMessage getMessage() {
        return message;
    }

    public void setMessage(PostCardMessage message) {
        this.message = message;
    }

    public PostCardAddress getAddress() {
        return address;
    }

    public void setAddress(PostCardAddress address) {
        this.address = address;
    }

    public void reset() {
        mInstance = null;
        PostCardController.sharedInstance();
    }
}