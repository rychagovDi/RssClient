package ru.rychagov.rssclient.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DataBaseHelper  extends SQLiteOpenHelper {

  private final static String DATABASE_NAME = "justRss.db";
  private final static int DATABASE_VERSION = 1;

  public DataBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    String SQL_CREATE_TABLE = "CREATE TABLE " + RssStreams.TABLE_NAME + " ("
            + RssStreams._ID + " INTEGER NOT NULL, "
            + RssStreams.COLUMN_RSS_TITLE + " TEXT NOT NULL, "
            + RssStreams.COLUMN_RSS_LINK + " TEXT NOT NULL);";

    db.execSQL(SQL_CREATE_TABLE);

    SQL_CREATE_TABLE = "CREATE TABLE " + RssEntry.TABLE_NAME + " ("
            + RssEntry._ID + " INTEGER NOT NULL, "
            + RssEntry.COLUMN_TITLE + " TEXT NOT NULL, "
            + RssEntry.COLUMN_DESCR + " TEXT NOT NULL, "
            + RssEntry.COLUMN_LINK + " TEXT NOT NULL, "
            + RssEntry.COLUMN_PUBDATE + " TEXT NOT NULL);";

    db.execSQL(SQL_CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public void addRssStream(String title, String link) {
    Cursor cursor = getReadableDatabase().query(
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

      getWritableDatabase().insert(RssStreams.TABLE_NAME, null, values);
    }
  }

  public static class RssStreams implements BaseColumns {
    public static final String TABLE_NAME = "rss_streams";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_RSS_TITLE = "rss_title";
    public static final String COLUMN_RSS_LINK = "rss_link";
  }

  public static class RssEntry implements BaseColumns {
    public static final String TABLE_NAME = "rss_entry";
    public static final String _ID = BaseColumns._ID;
    public final static String COLUMN_TITLE = "title";
    public final static String COLUMN_DESCR = "description";
    public final static String COLUMN_LINK = "link";
    public final static String COLUMN_PUBDATE = "date";
  }
}
