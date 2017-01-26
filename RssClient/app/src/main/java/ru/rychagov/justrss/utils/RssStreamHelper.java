package ru.rychagov.justrss.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ru.rychagov.justrss.data.DataBaseHelper;
import ru.rychagov.justrss.data.RssStream;

import static ru.rychagov.justrss.data.DataBaseHelper.*;

public class RssStreamHelper {

  public static ArrayList<RssStream> getStreams(Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

    Cursor cursor = db.query(RssStreams.TABLE_NAME, null, null, null, null, null, null);

    ArrayList<RssStream> streams = new ArrayList<>();

    if (cursor != null) {
      cursor.moveToPosition(-1);

      while (cursor.moveToNext()) {
        int id = cursor.getInt(cursor.getColumnIndex(RssStreams._ID));
        String title = cursor.getString(cursor.getColumnIndex(RssStreams.COLUMN_RSS_TITLE));
        String link = cursor.getString(cursor.getColumnIndex(RssStreams.COLUMN_RSS_LINK));

        streams.add(new RssStream(id, title, link));
      }

      cursor.close();
    }
    db.close();

    return streams;
  }

  public static void addRssStream(Context context, String title, String link) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
    Cursor cursor = db.query(
            RssStreams.TABLE_NAME,
            new String[] {String.format("MAX(%s)", RssStreams._ID)},
            null, null, null, null, null);

    if (cursor != null) {
      cursor.moveToFirst();
      int max_ID = cursor.getInt(cursor.getColumnIndex(String.format("MAX(%s)", RssStreams._ID)));
      cursor.close();

      ContentValues values = new ContentValues();
      values.put(RssStreams._ID, max_ID + 1);
      values.put(RssStreams.COLUMN_RSS_TITLE, title);
      values.put(RssStreams.COLUMN_RSS_LINK, link);

      db.insert(RssStreams.TABLE_NAME, null, values);
    }

    db.close();
  }

  public static void deleteRssStream(Context context, int _id) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

    db.delete(RssStreams.TABLE_NAME, RssStreams._ID + "=?", new String[]{"" + _id});
    db.delete(RssEntry.TABLE_NAME, RssEntry._ID + "=?", new String[]{"" + _id});

    db.close();
  }
}
