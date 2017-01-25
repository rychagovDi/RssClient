package ru.rychagov.justrss.utils;

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
}
