package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz_database";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_QUESTIONS = "quiz_questions";
    private static final String TABLE_QUIZZES = "quizzes";
    private static final String TABLE_RELATIONSHIP = "quiz_question_relationship";
    private static final String TABLE_USER_SCORES = "user_scores";

    // Table columns for quiz_questions
    private static final String KEY_QUESTION_ID = "question_id";

    private static final String KEY_STATE = "state";
    private static final String KEY_CAPITAL_CITY = "capital_city";
    private static final String KEY_ADDITIONAL_CITY1 = "additional_city1";
    private static final String KEY_ADDITIONAL_CITY2 = "additional_city2";

    // Table columns for quizzes
    private static final String KEY_QUIZ_ID = "quiz_id";
    private static final String KEY_QUIZ_DATE = "quiz_date";
    private static final String KEY_QUIZ_RESULT = "quiz_result";
    private static final String KEY_QUESTIONS_ANSWERED = "questions_answered";

    // Table columns for quiz_question_relationship
    private static final String KEY_USER_ANSWER = "user_answer";

    // Table columns for user_scores
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_SCORE = "user_score";

    // Create table queries
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + "(" +
            KEY_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

            KEY_STATE + " TEXT," +
            KEY_CAPITAL_CITY + " TEXT," +
            KEY_ADDITIONAL_CITY1 + " TEXT," +
            KEY_ADDITIONAL_CITY2 + " TEXT" +
            ")";

    private static final String CREATE_TABLE_QUIZZES = "CREATE TABLE " + TABLE_QUIZZES + "(" +
            KEY_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_QUIZ_DATE + " DATE," +
            KEY_QUIZ_RESULT + " INTEGER," +
            KEY_QUESTIONS_ANSWERED + " INTEGER" +
            ")";

    private static final String CREATE_TABLE_RELATIONSHIP = "CREATE TABLE " + TABLE_RELATIONSHIP + "(" +
            KEY_QUIZ_ID + " INTEGER," +
            KEY_QUESTION_ID + " INTEGER," +
            KEY_USER_ANSWER + " TEXT," +
            "PRIMARY KEY (" + KEY_QUIZ_ID + "," + KEY_QUESTION_ID + ")," +
            "FOREIGN KEY (" + KEY_QUIZ_ID + ") REFERENCES " + TABLE_QUIZZES + "(" + KEY_QUIZ_ID + ")," +
            "FOREIGN KEY (" + KEY_QUESTION_ID + ") REFERENCES " + TABLE_QUESTIONS + "(" + KEY_QUESTION_ID + ")" +
            ")";

    private static final String CREATE_TABLE_USER_SCORES = "CREATE TABLE " + TABLE_USER_SCORES + "(" +
            KEY_QUIZ_ID + " INTEGER," +
            KEY_QUESTION_ID + " INTEGER," +
            KEY_USER_ID + " INTEGER," +
            KEY_USER_SCORE + " INTEGER," +
            "PRIMARY KEY (" + KEY_QUIZ_ID + "," + KEY_QUESTION_ID + "," + KEY_USER_ID + ")," +
            "FOREIGN KEY (" + KEY_QUIZ_ID + ") REFERENCES " + TABLE_QUIZZES + "(" + KEY_QUIZ_ID + ")," +
            "FOREIGN KEY (" + KEY_QUESTION_ID + ") REFERENCES " + TABLE_QUESTIONS + "(" + KEY_QUESTION_ID + ")" +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_QUIZZES);
        db.execSQL(CREATE_TABLE_RELATIONSHIP);
        db.execSQL(CREATE_TABLE_USER_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZZES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELATIONSHIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_SCORES);
        onCreate(db);
    }

    public void insertQuestion(String country, String state, String capital, String city1, String city2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STATE, state);
        values.put(KEY_CAPITAL_CITY, capital);
        values.put(KEY_ADDITIONAL_CITY1, city1);
        values.put(KEY_ADDITIONAL_CITY2, city2);

        // Insert the row
        db.insert(TABLE_QUESTIONS, null, values);
        db.close(); // Closing database connection
    }
}
