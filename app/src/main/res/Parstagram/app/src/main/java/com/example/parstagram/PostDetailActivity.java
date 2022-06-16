package com.example.parstagram;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PostDetailActivity extends AppCompatActivity {
    private LinearLayout lv;
    protected TextView tvAuthor;
    protected ImageView ivPost;
    protected TextView tvCaption;
    protected TextView tvTimestamp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lv = findViewById(R.id.lv);
        tvAuthor = findViewById(R.id.tvAuthor);
        ivPost = findViewById(R.id.ivPost);
        tvCaption = findViewById(R.id.tvCaption);
        tvTimestamp = findViewById(R.id.tvTimestamp);

    }
}
