package vn.nguyenbuiquanghuy.englishquiz_app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz_app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_HISTORY = "quiz_history";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TOPIC = "topic";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_TOTAL_QUESTIONS = "total_questions";
    public static final String COLUMN_DATE_TIME = "date_time";

    public HistoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_HISTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOPIC + " TEXT NOT NULL, " +
                COLUMN_SCORE + " INTEGER NOT NULL, " +
                COLUMN_TOTAL_QUESTIONS + " INTEGER NOT NULL, " +
                COLUMN_DATE_TIME + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }

    public long insertQuizResult(String topic, int score, int totalQuestions, String dateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOPIC, topic);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_TOTAL_QUESTIONS, totalQuestions);
        values.put(COLUMN_DATE_TIME, dateTime);
        return db.insert(TABLE_HISTORY, null, values);
    }

    public Cursor getAllQuizResults() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_HISTORY, null, null, null, null, null, COLUMN_DATE_TIME + " DESC");
    }
}
