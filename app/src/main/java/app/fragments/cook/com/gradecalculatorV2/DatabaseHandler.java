package app.fragments.cook.com.gradecalculatorV2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbcoo_000 on 7/24/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper implements Serializable {
    //database version/ Update whenever a change is made to the database structure
    private static final int VERSION = 10;

    private static final String DATABASE_NAME = "gradesList";
    //table names
    private static final String TABLE_ONE = "Class_One";
    private static final String TABLE_TWO = "Class_Two";
    private static final String TABLE_THREE = "Class_Three";
    private static final String TABLE_FOUR = "Class_Four";
    private static final String TABLE_FIVE = "Class_Five";
    private static final String TABLE_SIX = "Class_Six";
    private static final String TABLE_SEVEN = "Class_Seven";
    //common column names



    private static final String GRADE_ID = "id";
    private static final String ASSIGNMENT_NAME = "Assignment";
    private static final String GRADE_NAME = "Name";
    private static final String GRADE_VALUE = "Grade";
    public static final String[] ALL_KEYS = new String[] {GRADE_ID, ASSIGNMENT_NAME, GRADE_NAME, GRADE_VALUE};
    //Table Create Statements
    private static final String CREATE_TABLE_ONE = "CREATE TABLE " + TABLE_ONE + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";
    private static final String CREATE_TABLE_TWO = "CREATE TABLE " + TABLE_TWO + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";
    private static final String CREATE_TABLE_THREE = "CREATE TABLE " + TABLE_THREE + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";
    private static final String CREATE_TABLE_FOUR = "CREATE TABLE " + TABLE_FOUR + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";
    private static final String CREATE_TABLE_FIVE = "CREATE TABLE " + TABLE_FIVE + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";
    private static final String CREATE_TABLE_SIX = "CREATE TABLE " + TABLE_SIX + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";
    private static final String CREATE_TABLE_SEVEN = "CREATE TABLE " + TABLE_SEVEN + "(" + GRADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT_NAME +
            " TEXT, " + GRADE_NAME + " TEXT, " + GRADE_VALUE + " INTEGER, " + "UNIQUE(" + ASSIGNMENT_NAME + ", " + GRADE_NAME + ", " + GRADE_VALUE + ") ON CONFLICT IGNORE);";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ONE);
        db.execSQL(CREATE_TABLE_TWO);
        db.execSQL(CREATE_TABLE_THREE);
        db.execSQL(CREATE_TABLE_FOUR);
        db.execSQL(CREATE_TABLE_FIVE);
        db.execSQL(CREATE_TABLE_SIX);
        db.execSQL(CREATE_TABLE_SEVEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TWO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THREE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEVEN);
        onCreate(db);
    }
    public void addGrade(Grade grade, int subjectId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(subjectId == 1){
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_ONE, null, values);                         //Insert Row
            db.close();
        }else if(subjectId == 2){
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_TWO, null, values);
            db.close();
        }else if(subjectId == 3) {
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_THREE, null, values);
            db.close();
        }else if(subjectId == 4) {
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_FOUR, null, values);
            db.close();
        }else if(subjectId == 5){
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_FIVE, null, values);
            db.close();
        }else if(subjectId == 6) {
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_SIX, null, values);
            db.close();
        }else {
            values.put(ASSIGNMENT_NAME, grade.getAssignment());
            values.put(GRADE_NAME, grade.getGradeName());
            values.put(GRADE_VALUE, grade.getGradeValue());
            db.insert(TABLE_SEVEN, null, values);
            db.close();
        }
    }
    public List<Grade> getAllContacts(int subjectId/*, String assignment*/){
        List<Grade> gradesList = new ArrayList<Grade>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery;
        if(subjectId == 1) {
            selectQuery = "SELECT  * FROM " + TABLE_ONE;
        }else if(subjectId == 2){
            selectQuery = "SELECT  * FROM " + TABLE_TWO;
        }else if(subjectId == 3){
            selectQuery = "SELECT  * FROM " + TABLE_THREE;
        }else if(subjectId == 4){
            selectQuery = "SELECT  * FROM " + TABLE_FOUR;
        }else if (subjectId == 5){
            selectQuery = "SELECT  * FROM " + TABLE_FIVE;
        }else if(subjectId == 6) {
            selectQuery = "SELECT  * FROM " + TABLE_SIX;
        }else {
            selectQuery = "SELECT  * FROM " + TABLE_SEVEN;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                Grade grade = new Grade();
                //if(assignment.equals(cursor.getString(1))) {
                    grade.setId(Integer.parseInt(cursor.getString(0)));
                    grade.setAssignment(cursor.getString(1));
                    grade.setGradeName(cursor.getString(2));
                    grade.setGradeValue(Float.parseFloat(cursor.getString(3)));
                    gradesList.add(grade);
                //}
            }while(cursor.moveToNext());
        }
        Log.d("Initial_cursor_CHECK", Integer.toString(cursor.getCount()));
        cursor.close();
        return gradesList;
    }
    public void deleteGrade(String table, String name){                                             //Delete single grade
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, GRADE_NAME + "='" + name + "'", null);

    }
    public void deleteAssignment(String table, String assignmentName){                              //Delete Assignment and grades within it
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table + " WHERE " + ASSIGNMENT_NAME + "='"+assignmentName+"'");
    }
    public boolean deleteRow(long rowId, String table){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = GRADE_ID + "=" + rowId;
        return db.delete(table, where, null) != 0;

    }
    public void deleteTable(String table){
        Cursor c = getAllRows(table);
        long rowId = c.getColumnIndexOrThrow(GRADE_ID);
        if(c.moveToFirst()){
            do{
                deleteRow(c.getLong((int) rowId), table);
            }while(c.moveToNext());
        }
        c.close();
    }
    public Cursor getAllRows(String table) {                                                    //Collects every row in the specified table into a cursor
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor c = db.query(true, TABLE_ONE, ALL_KEYS, where, null, null, null, null, null);
        Cursor c = db.rawQuery("SELECT  * FROM " + table, null);
        Log.d("Initial_cursor_TWO", Integer.toString(c.getCount()));
        if(c != null){
            c.moveToFirst();
        }
        Log.d("Initial_cursor_TWO", Integer.toString(c.getCount()));
        Log.d("DATABSE_CHECK", DatabaseUtils.dumpCursorToString(c));
        return c;
    }

}
