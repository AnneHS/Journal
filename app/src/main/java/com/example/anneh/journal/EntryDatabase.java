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

    // constructor
    private EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {

        if (instance == null) {
            return instance = new EntryDatabase(context, "entries_db" , null, 1);
            // --> private instance variable
        }
        else {
            return instance;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create entries table
        String createTable = "CREATE TABLE entries(_id INTEGER PRIMARY KEY, title TEXT, content TEXT, mood TEXT, timestamp TEXT)";
        db.execSQL(createTable);
        // db.execSQL("create table entries (id INTEGER PRIMARY KEY, title TEXT, content TEXT, mood TEXT, timestamp TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop and recreate entries table
        db.execSQL("DROP TABLE entries");

        // call onCreate to start with clean slate?
        onCreate(db);
    }

    public Cursor selectAll() {

        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    public void insert(JournalEntry entry) {
        //
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.title);
        contentValues.put("content", entry.content);
        contentValues.put("mood", entry.mood);
        contentValues.put("timestamp", entry.timestamp);

        db.insert("entries", null, contentValues);
        // db.close();
    }

    public void delete(long id) {
        //
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("entries", "_id = ?", new String[] {String.valueOf(id)});

        // db.execSQL"DELETE FROM entries WHERE _id=JournalEntry.getId()");
    }

}
