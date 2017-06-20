package com.example.y.androidinsertblobdatainandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText editText1, editText2, editText3;
    ImageView imageView;
    Employee employee;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.iv_user_photo);
        editText1 = (EditText) findViewById(R.id.ev_name);
        editText2 = (EditText) findViewById(R.id.ev_age);
        editText3 = (EditText) findViewById(R.id.ev_sex);

        Log.d("Insert: ", "Inserting ..");
        // Saving to Database...
        dbHelper = new DBHelper(this);
        if(dbHelper.numberOfRows()>0){
            Log.e("MA ","Database already exist.");
        }
        else{
            saveImageInDB();
            Log.e("MA ","Image Saved in Database.");

        }

        //blob to image conversion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadImageFromDB()) {
                    Log.e("MA ","Image Loaded from Database.");
                }
            }
        }, 0);
    }
    Boolean saveImageInDB() {
        employee = new Employee();
        employee.setEmployeeName("Alexandra Dadario");
        employee.setEmployeeAge("31");
        employee.setEmployeeSex("FeMALE");

        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
        employee.employeeimageInByte = imageInByte;


        Log.d("Insert: ", "Inserting ..");
        dbHelper.insertEmployee(employee);
        return true;

    }

    Boolean loadImageFromDB() {
        try {
            ArrayList array_list = dbHelper.getAllEmployee();
            Log.e("Employee Size ", String.valueOf(array_list.size()));

            if(!array_list.isEmpty()){
                Employee employee1 = (Employee) array_list.get(0);

                editText1.setText(employee1.employeeName);
                editText2.setText(employee1.employeeAge);
                editText3.setText(employee1.employeeSex);
                imageView.setImageBitmap(Utils.getImage(employee1.getEmployeeimageInByte()));

            }else {
                Log.e("MA ", "No Employee available ");
            }

            return true;
        } catch (Exception e) {
            Log.e("MA ", "<loadImageFromDB> Error : " + e.getLocalizedMessage());

            return false;
        }
    }

}
