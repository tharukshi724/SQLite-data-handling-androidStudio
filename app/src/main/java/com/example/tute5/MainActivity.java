package com.example.tute5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tute5.Database.DBhelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextTextuser);
        password = findViewById(R.id.editTextTextPassword);

    }

    public void addData(View view){
        DBhelper dBhelper = new DBhelper(this);

        long val =  dBhelper.addInfo(username.getText().toString(),password.getText().toString());
        if(val>0){
            Toast.makeText(this,"DATA insertede",Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(this,"DATA not inserted",Toast.LENGTH_LONG).show();
            
        }

    }

    public void readAll(View view){
        DBhelper dBhelper = new DBhelper(this);

        List a = dBhelper.readAllinfo();
    }

    public void delete(View view){
        DBhelper dBhelper = new DBhelper(this);

        int a = dBhelper.deleteInfo((username.getText().toString()));

        if(a > 0){
            Toast.makeText(this,"succesfully deleted",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"deletion is unsucceful",Toast.LENGTH_LONG).show();
        }
    }

    //update

    public void update(View view){

        DBhelper dBhelper = new DBhelper(this);

        dBhelper.updateInfo(username.getText().toString(),password.getText().toString());

    }





}