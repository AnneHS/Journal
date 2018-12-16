package com.example.anneh.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    private EntryAdapter instance;

    // constructor
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        TextView tvTimestamp = (TextView) view.findViewById(R.id.timestamp);
        TextView tvSummary = (TextView) view.findViewById(R.id.summary);
        ImageView ivMood = (ImageView) view.findViewById(R.id.mood);

        // Extract properties from cursor
        String dbTitle = cursor.getString(cursor.getColumnIndex("title"));
        String dbTimestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
        String dbMood = cursor.getString(cursor.getColumnIndex("mood"));

        // Populate fields with extracted properties
        tvTitle.setText(dbTitle);
        tvTimestamp.setText(dbTimestamp);

        // Set image resource for mood and rotate image by 90 degrees
        // https://stackoverflow.com/questions/9551034/get-resource-image-by-name-into-custom-cursor-adapter
        int image_id = context.getResources().getIdentifier(dbMood, "drawable", context.getPackageName());
        ivMood.setImageResource(image_id);
        ivMood.setRotation(90F);
        tvSummary.setText(dbMood);
    }
}
