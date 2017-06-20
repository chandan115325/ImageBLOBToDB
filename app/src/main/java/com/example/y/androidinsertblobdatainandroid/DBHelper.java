package com.example.y.androidinsertblobdatainandroid;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    //String Constants to name the columns in table
    public static final String DATABASE_NAME ="employeeDBName.db";
    public static final String EMPLOYEE_TABLE_NAME = "employees";
    public static final String EMPLOYEE_COLUMN_ID = "id";
    public static final String EMPLOYEEs_COLUMN_NAME = "employeeName";
    public static final String EMPLOYEEs_COLUMN_AGE = "employeeAge";
    public static final String EMPLOYEEs_COLUMN_SEX = "employeeSex";
    private static final String EMPLOYEEs_COLUMN_IMAGE = "employeeImage";
    private static final String CREATE_IMAGES_TABLE =
            "CREATE TABLE " + EMPLOYEE_TABLE_NAME + " (" +
                    EMPLOYEE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EMPLOYEEs_COLUMN_NAME + " text,"
                    + EMPLOYEEs_COLUMN_AGE + " text,"
                    + EMPLOYEEs_COLUMN_SEX + " text,"
                    + EMPLOYEEs_COLUMN_IMAGE + " BLOB NOT NULL );";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }
//Creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_IMAGES_TABLE);

    }
//To upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS employees");
        onCreate(db);

    }

    /**
     * Inserting records into the table
     * @param employeeName
     * @param employeeAge
     * @param employeeSex
     * @param employeeimageInByte
     * @return
     */
    public boolean insertEmployee  (String employeeName, String employeeAge, String employeeSex, byte[] employeeimageInByte) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("employeeName", employeeName);
        contentValues.put("employeeAge", employeeAge);
        contentValues.put("employeeSex", employeeSex);
        contentValues.put("employeeImage", employeeimageInByte);
        db.insert("employees", null, contentValues);
        return true;
    }

    public boolean insertEmployee  (Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEEs_COLUMN_NAME, employee.employeeName);
        contentValues.put(EMPLOYEEs_COLUMN_AGE, employee.employeeAge);
        contentValues.put(EMPLOYEEs_COLUMN_SEX, employee.employeeSex);
        contentValues.put(EMPLOYEEs_COLUMN_IMAGE, employee.employeeimageInByte);
        db.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from employees where id=" + id + "", null );
        return res;
    }

    public int numberOfRows(){
        int numRows;
        SQLiteDatabase db = this.getReadableDatabase();

        return numRows = (int) DatabaseUtils.queryNumEntries(db, EMPLOYEE_TABLE_NAME);
    }

    public boolean updateEmployee (Integer id, String employeeName,String employeeAge, String employeeSex)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("employeeName", employeeName);
        contentValues.put("employeeAge", employeeAge);
        contentValues.put("employeeSex", employeeSex);
        db.update("employees", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteEmployee (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("employees",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Employee> getAllEmployee()
    {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT * FROM employees";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setEmployeeName(cursor.getString(1));
                employee.setEmployeeAge(cursor.getString(2));
                employee.setEmployeeSex(cursor.getString(3));
                employee.setEmployeeimageInByte(cursor.getBlob(4));
                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return employeeList;

    }
}
