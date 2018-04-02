package com.example.yangm89.myfirstapplication;

import android.content.Intent;
import android.content.res.Configuration;
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
    public final static String key_username = "local_username_key";

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
        //if register is checked
        if(register.isChecked()){
            String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();
            if(username.length() >= 4){
                if(password.length() >= 6){
                    if(isTextValid(password)){
                        intent.putExtra(key_username, username);
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
                Toast.makeText(this,
                        "Username must contain at least 4 characters",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else if(login.isChecked()){
            String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();
            if(username.equals("Erik")){
                if(password.equals("Krohn1")){
                    intent.putExtra(key_username, username);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,
                            "Incorrect username and password",
                            Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this,
                        "Incorrect username and password",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isTextValid(String textToCheck) {
        return textPattern.matcher(textToCheck).matches();
    }
}
