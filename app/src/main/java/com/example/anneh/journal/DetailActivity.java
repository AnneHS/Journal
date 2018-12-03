package com.example.anneh.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    JournalEntry retrievedEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = (String) intent.getSerializableExtra("title");
        // String mood = (String) intent.getSerializableExtra("mood");
        String timestamp = (String) intent.getSerializableExtra("timestamp");
        String content = (String) intent.getSerializableExtra("content");

        TextView titleTV = (TextView) findViewById(R.id.title_detail);
        TextView timestampTV = (TextView) findViewById(R.id.timestamp_detail);
        TextView contentTV = (TextView) findViewById(R.id.content_detail);
        // ImageView moodIV = (ImageView) findViewById(R.id.mood);

        titleTV.setText(title);
        timestampTV.setText(timestamp);
        contentTV.setText(content);
        // mood

    }
}
