package com.example.user.anniefyppostcard.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.anniefyppostcard.PostCardMessage;
import com.example.user.anniefyppostcard.R;
import com.example.user.anniefyppostcard.fragments.PostCardController;

/**
 * Created by roytang on 13/3/2016.
 */
public class EditMessageActivity extends AppCompatActivity {
    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_message);

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editText = (EditText) findViewById(R.id.inputTextView);
        saveButton = (Button) findViewById(R.id.saveButton);

        // get text from controller if available
        PostCardController controller = PostCardController.sharedInstance();
        if (controller.getMessage() != null) {
            editText.setText(controller.getMessage().getText());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editText.getText().toString();

                if (message.length() == 0) {
                    message = "";
                }
                PostCardController controller = PostCardController.sharedInstance();
                controller.setMessage(new PostCardMessage(message));

                // dismiss activity when done
                EditMessageActivity.this.finish();
            }
        });
    }
}
