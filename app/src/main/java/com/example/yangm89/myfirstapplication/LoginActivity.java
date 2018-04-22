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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
    String serverResponse = "";

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

        CheckBox register = ((CheckBox)findViewById(R.id.checkBox_register));
        CheckBox login = ((CheckBox)findViewById(R.id.checkBox_login));

        MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(getApplicationContext());
        SQLiteDatabase db = mdbh.getWritableDatabase();
        SQLiteDatabase rdb = mdbh.getReadableDatabase();
        ContentValues values = new ContentValues();

        //if register is checked
        if(register.isChecked()){
            final String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            final String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();

            if(username.length() >= 4){
                if(password.length() >= 6){
                    if(isTextValid(password)){

                        register(username, password);
                    }
                    else{
                        Toast.makeText(this, "Password must contain a capital letter, lowercase letter, and a number.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Password must be at least 6 characters in length.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Username must be at least 4 characters in length.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(login.isChecked()){
            //get the typed in username and password
            String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();

            //check username and password against the database
            String selection = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " GLOB ? " ;
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
                            Intent intent = new Intent(this, MainActivity.class);
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

    private boolean isTextValid(String textToCheck) {
        return textPattern.matcher(textToCheck).matches();
    }

    private void register(final String username, final String password){

        RequestQueue queue = Volley.newRequestQueue(this);
       // queue.start();

        Toast.makeText(LoginActivity.this, "PASSWORD " + password, Toast.LENGTH_LONG).show();
        Toast.makeText(LoginActivity.this, "USERNAME " + username, Toast.LENGTH_LONG).show();

        String url = "http://webdev.cs.uwosh.edu/students/yangm89/AndroidPHP/register_query.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this, "RESPONSE " + response, Toast.LENGTH_LONG).show();
                       setServerResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, "There was an error connecting to the database. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        queue.add(stringRequest);
        if(serverResponse.equals("Username already exists.")){
            Toast.makeText(this , serverResponse, Toast.LENGTH_SHORT).show();
        }
        else {
            if(serverResponse.equals("Successfully registered and signed in.")){
                Toast.makeText(this, serverResponse, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constants.key_username, username);
                startActivity(intent);
                //finish();
            }
        }

    }

    private String getResponse(){
        return serverResponse;
    }

    private void setServerResponse(String r){
        serverResponse = r;
    }

}
