package com.example.anneh.journal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    Cursor cursor;
    private EntryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());
        cursor = db.selectAll();
        adapter = new EntryAdapter(this, cursor);

        // Get reference to listview & set adapter to listview
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new ListViewClickListener());
        listview.setOnItemLongClickListener(new ListViewLongClickListener());

    }

    public void actionClicked(View view) {
        /** continue to next activity */

        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // fires an Intent to the third activity that shows the entry details
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // Add the instance of Entry that was clicked
            Cursor clickedEntry = (Cursor) adapterView.getItemAtPosition(position);

            intent.putExtra("title", clickedEntry.getString(clickedEntry.getColumnIndex("title")));
            intent.putExtra("mood", clickedEntry.getString(clickedEntry.getColumnIndex("mood")));
            intent.putExtra("timestamp", clickedEntry.getString(clickedEntry.getColumnIndex("timestamp")));
            intent.putExtra("content", clickedEntry.getString(clickedEntry.getColumnIndex("content")));

            startActivity(intent);
        }
    }

    // delete item from listview
    private class ListViewLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

            // return item of type cursor (from adapter)
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);

            // get id from table entries in db
            long id_= cursor.getLong(cursor.getColumnIndex("id"));

            // delete id-column fromd database
            db.delete(id_); // ????? ---> long id
            updateData();
            return true;
        }
    }

    private void updateData() {
        Cursor new_cursor = db.selectAll();
        adapter.swapCursor(new_cursor);
    }


    public void onResume() {
        super.onResume(); // WAT IS SUPER EIGENLIJK?
    }



}
