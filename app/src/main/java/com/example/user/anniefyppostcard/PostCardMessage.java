package com.example.user.anniefyppostcard;

/**
 * Created by roytang on 13/3/2016.
 */
public class PostCardMessage {
    private String text;

    public PostCardMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
