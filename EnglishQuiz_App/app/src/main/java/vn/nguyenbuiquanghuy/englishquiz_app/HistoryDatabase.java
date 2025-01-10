package vn.nguyenbuiquanghuy.englishquiz_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "quiz_history.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_HISTORY = "history";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TOPIC = "topic";
    private static final String COLUMN_CORRECT_COUNT = "correct_count";
    private static final String COLUMN_TOTAL_QUESTIONS = "total_questions";
    private static final String COLUMN_DATE = "date";

    public HistoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_HISTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOPIC + " TEXT, " +
                COLUMN_CORRECT_COUNT + " INTEGER, " +
                COLUMN_TOTAL_QUESTIONS + " INTEGER, " +
                COLUMN_DATE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    public void addHistory(String topic, int correctCount, int totalQuestions, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOPIC, topic);
        values.put(COLUMN_CORRECT_COUNT, correctCount);
        values.put(COLUMN_TOTAL_QUESTIONS, totalQuestions);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    public Cursor getAllHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);
    }
}
