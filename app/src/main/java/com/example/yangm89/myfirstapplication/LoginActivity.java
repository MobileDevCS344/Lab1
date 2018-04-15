package com.example.yangm89.myfirstapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_login_landscape);
        } else {
            setContentView(R.layout.activity_login_portrait);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



    }

    public void registerClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            ((CheckBox)findViewById(R.id.checkBox_login)).setChecked(false);
            ((Button)findViewById(R.id.button_login)).setText(R.string.register);
        }
    }

    public void loginClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            ((CheckBox)findViewById(R.id.checkBox_register)).setChecked(false);
            ((Button)findViewById(R.id.button_login)).setText(R.string.login);
        }
    }

    public void goToMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        CheckBox register = ((CheckBox)findViewById(R.id.checkBox_register));
        CheckBox login = ((CheckBox)findViewById(R.id.checkBox_login));

        MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(getApplicationContext());
        SQLiteDatabase db = mdbh.getWritableDatabase();
        SQLiteDatabase rdb = mdbh.getReadableDatabase();
        ContentValues values = new ContentValues();



        //if register is checked
        if(register.isChecked()){
            String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();

            String selection = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " LIKE ? " ;
            String[] selectionArgs = { username };
            String[] projection = {MyDBContract.DBEntry.COLUMN_NAME_USER_ID, MyDBContract.DBEntry.COLUMN_NAME_PASSWORD};
            String sortOrder = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " DESC";
            Cursor cursor = rdb.query(MyDBContract.DBEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);

            if(username.length() >= 4){
                //check if username already exists
                if(cursor.getCount() == 0){
                    if(password.length() >= 6){
                        if(isTextValid(password)){


                            //add the user info to the values
                            values.put(MyDBContract.DBEntry._ID, 1);
                            values.put(MyDBContract.DBEntry.COLUMN_NAME_USER_ID, username);
                            values.put(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD, password);
                            //create a row in the database
                            long rowId = db.insert(MyDBContract.DBEntry.TABLE_NAME, null, values);

                            intent.putExtra(Constants.key_username, username);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(this,
                                    "Password must contain a capital letter, lowercase letter, and a number",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this,
                                "Password must contain at least 6 characters",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    cursor.moveToFirst();
                    while(!cursor.isAfterLast()) {
                        String id = cursor.getString(cursor.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_USER_ID));
                        if(id.equals(username)){
                            Toast.makeText(this,
                                    "The username " + username + " is already taken.",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else {
                            if(password.length() >= 6){
                                if(isTextValid(password)){
                                    //add the user info to the values
                                    values.put(MyDBContract.DBEntry._ID, 1);
                                    values.put(MyDBContract.DBEntry.COLUMN_NAME_USER_ID, username);
                                    values.put(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD, password);
                                    //create a row in the database
                                    db.insert(MyDBContract.DBEntry.TABLE_NAME, null, values);

                                    intent.putExtra(Constants.key_username, username);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(this,
                                            "Password must contain a capital letter, lowercase letter, and a number",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(this,
                                        "Password must contain at least 6 characters",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        cursor.moveToNext();
                    }
                }
            }
            else {
                Toast.makeText(this,
                        "Username must contain at least 4 characters.",
                        Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        }
        else if(login.isChecked()){
            //get the typed in username and password
            String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();

            //check username and password against the database
            String selection = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " LIKE ? " ;
            String[] selectionArgs = { username };
            String[] projection = {MyDBContract.DBEntry.COLUMN_NAME_USER_ID, MyDBContract.DBEntry.COLUMN_NAME_PASSWORD};
            String sortOrder = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " DESC";
            Cursor cursor = rdb.query(MyDBContract.DBEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
            if(cursor.getCount()==0){
                Toast.makeText(this,
                        "Username " + username + " does not exist. Please register first.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_USER_ID));
                    String pw = cursor.getString(cursor.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD));

                    //check if the id and password is valid
                    if(username.equals(id)){
                        if(password.equals(pw)){
                            intent.putExtra(Constants.key_username, username);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(this,
                                    "Incorrect username and password combination.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this,
                                "Incorrect username. Please check the spelling of the username",
                                Toast.LENGTH_SHORT).show();
                    }

                    cursor.moveToNext();
                }

                cursor.close();
            }


        }
    }

    public boolean isTextValid(String textToCheck) {
        return textPattern.matcher(textToCheck).matches();
    }
}
