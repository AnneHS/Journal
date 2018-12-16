package com.example.anneh.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;
    SQLiteDatabase db;

    // Constructor
    private EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Getter
    public static EntryDatabase getInstance(Context context) {

        if (instance == null) {
            return instance = new EntryDatabase(context, "entries_db" , null, 2);
        }
        else {
            return instance;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create entries table
        String createTable = "CREATE TABLE entries(_id INTEGER PRIMARY KEY, title TEXT, content TEXT, mood TEXT, timestamp TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop and reload entries table
        db.execSQL("DROP TABLE entries");
        onCreate(db);
    }

    public Cursor selectAll() {
        // Get cursor for database
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    public void insert(JournalEntry entry) {

        // Insert entry into database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.title);
        contentValues.put("content", entry.content);
        contentValues.put("mood", entry.mood);
        contentValues.put("timestamp", entry.timestamp);

        db.insert("entries", null, contentValues);
    }

    public void delete(long id) {

        // Delete entry from database
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("entries", "_id = ?", new String[] {String.valueOf(id)});
    }

}
