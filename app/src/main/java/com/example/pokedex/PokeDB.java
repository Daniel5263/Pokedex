package com.example.pokedex;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PokeDB extends ContentProvider {
    private static final String AUTHORITY = "com.example.pokedex.provider";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("data").build();
    private static final int DATA = 1;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private static final String DATABASE_NAME = "pokedex.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "pokemon_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NATIONAL = "national_number";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SPECIES = "species";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_HP = "hp";
    public static final String COLUMN_ATTACK = "attack";
    public static final String COLUMN_DEFENSE = "defense";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + " ("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_NATIONAL + " integer not null, "
            + COLUMN_SPECIES + " text not null, "
            + COLUMN_GENDER + " text not null, "
            + COLUMN_WEIGHT + " real not null, "
            + COLUMN_HEIGHT + " real not null, "
            + COLUMN_LEVEL + " integer not null, "
            + COLUMN_HP + " integer not null, "
            + COLUMN_ATTACK + " integer not null, "
            + COLUMN_DEFENSE + " integer not null)";

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "data", DATA);
    }

    public PokeDB() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int match = sUriMatcher.match(uri);
        int rowsDeleted = 0;

        if (match == DATA) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            rowsDeleted = db.delete(TABLE_NAME, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA:
                return "vnd.android.cursor.dir/vnd.com.example.pokedex.data";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        Uri insertedItemUri = null;

        if (match == DATA) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long id = db.insert(TABLE_NAME, null, values);
            if (id != -1) {
                insertedItemUri = ContentUris.withAppendedId(CONTENT_URI, id);
                getContext().getContentResolver().notifyChange(insertedItemUri, null);
            }
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return insertedItemUri;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int match = sUriMatcher.match(uri);
        Cursor cursor;

        if (match == DATA) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        int rowsUpdated = 0;

        switch (match) {
            case DATA:
                rowsUpdated = database.update("pokemon_data", values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return rowsUpdated;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}