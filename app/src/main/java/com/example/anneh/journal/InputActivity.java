package com.example.anneh.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {

    EntryDatabase db;
    String mood;
    Boolean moodclicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // SetOnClickListeners for mood buttons (https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android)
        ImageButton happy = (ImageButton) findViewById(R.id.happy);
        ImageButton sleep = (ImageButton) findViewById(R.id.sleep);
        ImageButton cry = (ImageButton) findViewById(R.id.cry);
        ImageButton dead = (ImageButton) findViewById(R.id.dead);

        happy.setOnClickListener(this);
        sleep.setOnClickListener(this);
        cry.setOnClickListener(this);
        dead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // Get mood
        switch (view.getId()) {
            case R.id.happy:
                mood = "happy";
                moodclicked = true;
                break;
            case R.id.sleep:
                mood = "sleep";
                moodclicked = true;
                break;
            case R.id.cry:
                mood = "cry";
                moodclicked = true;
                break;
            case R.id.dead:
                mood = "dead";
                moodclicked = true;
                break;
        }
    }

    public void addEntry(View view) {

        // Check if user has clicked mood
        if (moodclicked == false) {
            Toast.makeText(InputActivity.this, "enter mood", Toast.LENGTH_LONG).show();
        }
        else {
            // Reset moodclicked for next entry
            moodclicked = false;

            // Pass entry to database
            db = EntryDatabase.getInstance(getApplicationContext());

            // Title
            EditText title_ET = (EditText) findViewById(R.id.title);
            String title = title_ET.getText().toString();

            // Content
            EditText content_ET = (EditText) findViewById(R.id.content);
            String content = content_ET.getText().toString();

            // Timestamp
            Date currentTime = Calendar.getInstance().getTime();
            String timestamp = currentTime.toString();

            JournalEntry entry = new JournalEntry(title, content, mood, timestamp);
            db.insert(entry);

            // Return to MainActivity
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }
}
