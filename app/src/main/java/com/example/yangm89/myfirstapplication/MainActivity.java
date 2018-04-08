package com.example.yangm89.myfirstapplication;


import android.content.Intent;
import android.content.res.Configuration;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        LinearLayoutFragment.OnFragmentInteractionListener, TableFragment.OnFragmentInteractionListener {
    private ImageView c1, c2, c3;
    private ArrayList<String> chatHistory, mathHistory;
    private int correct_answer = -1, player_answer = -2;
    private String username;
    private boolean tableVisible;
    private LinearLayoutFragment mathfragment;
    private TableFragment tableFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
        } else {
            setContentView(R.layout.activity_portrait);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.key_username);
        ((TextView)findViewById(R.id.textView_p1Label)).setText(username);
        ((TextView)findViewById(R.id.textView_p1CardRemLabel)).setText(username);

        //random image generator
        generateRandomImg();
        c1 = (ImageView) findViewById(R.id.imageView_card1);
        c2 = (ImageView) findViewById(R.id.imageView_card2);
        c3 = (ImageView) findViewById(R.id.imageView_card3);
        chatHistory = new ArrayList();
        mathHistory = new ArrayList();
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);


        //add the table fragment to the screen
        tableFragment = new TableFragment();
        tableFragment.setArguments(getIntent().getExtras());
        android.support.v4.app.FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        f.add(R.id.fragment_container, tableFragment);
        f.commit();

        //set bidding boolean to true because we are initially bidding
        tableVisible = true;
    }

    @Override
    public void onStart(){
        super.onStart();;
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageView_card1:
                if(c1.getBackground() != null ) {
                    if(((ColorDrawable)c1.getBackground()).getColor() == 0){
                        c1.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                        if(c2.getBackground() != null) {
                            c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                        if(c3.getBackground() != null) {
                            c3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                    }
                    else {
                        if(((ColorDrawable)c1.getBackground()).getColor() == getResources().getColor(R.color.cardBackgroundSkyBlue)) {
                            ImageView table1 = (ImageView) findViewById(R.id.imageView_p1Play);
                            ImageView table2 = (ImageView) findViewById(R.id.imageView_p2Play);
                            if(table1.getDrawable() == null){
                                table1.setImageDrawable(c1.getDrawable());
                                c1.setImageResource(android.R.color.transparent);
                            }
                            else if(table2.getDrawable() == null){
                                table2.setImageDrawable(c1.getDrawable());
                                c1.setImageResource(android.R.color.transparent);
                            }
                        }
                    }
                }
                else {
                    c1.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                    if(c2.getBackground() != null) {
                        c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                    if(c3.getBackground() != null) {
                        c3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                }

                break;

            case R.id.imageView_card2:
                if(c2.getBackground() != null ) {
                    if(((ColorDrawable)c2.getBackground()).getColor() == 0){
                        c2.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                        if(c1.getBackground() != null) {
                            c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                        if(c3.getBackground() != null) {
                            c3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                    }
                    else {
                        if(((ColorDrawable)c2.getBackground()).getColor() == getResources().getColor(R.color.cardBackgroundSkyBlue)) {
                            ImageView table1 = (ImageView) findViewById(R.id.imageView_p1Play);
                            ImageView table2 = (ImageView) findViewById(R.id.imageView_p2Play);
                            if(table1.getDrawable() == null){
                                table1.setImageDrawable(c2.getDrawable());
                                c2.setImageResource(android.R.color.transparent);
                            }
                            else if(table2.getDrawable() == null){
                                table2.setImageDrawable(c2.getDrawable());
                                c2.setImageResource(android.R.color.transparent);
                            }
                        }
                    }
                }
                else {
                    c2.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                    if(c1.getBackground() != null) {
                        c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                    if(c3.getBackground() != null) {
                        c3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                }

                break;

            case R.id.imageView_card3:
                if(c3.getBackground() != null ) {
                    if(((ColorDrawable)c3.getBackground()).getColor() == 0){
                        c3.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                        if(c1.getBackground() != null) {
                            c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                        if(c2.getBackground() != null) {
                            c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                    }
                    else {
                        if(((ColorDrawable)c3.getBackground()).getColor() == getResources().getColor(R.color.cardBackgroundSkyBlue)) {
                            ImageView table1 = (ImageView) findViewById(R.id.imageView_p1Play);
                            ImageView table2 = (ImageView) findViewById(R.id.imageView_p2Play);
                            if(table1.getDrawable() == null){
                                table1.setImageDrawable(c3.getDrawable());
                                c3.setImageResource(android.R.color.transparent);
                            }
                            else if(table2.getDrawable() == null){
                                table2.setImageDrawable(c3.getDrawable());
                                c3.setImageResource(android.R.color.transparent);
                            }
                        }
                    }
                }
                else {
                    c3.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                    if(c1.getBackground() != null) {
                        c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                    if(c2.getBackground() != null) {
                        c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                }

                break;
        }
    }

    public void sendMessage(View v) {
        //handle chat messages

        String msg = ((EditText)findViewById(R.id.editText_chatMsg)).getText().toString();
        String temp;
        StringBuilder s = new StringBuilder();
        chatHistory.add(msg);
        if(msg.trim().toLowerCase().equals("math")){
     /*       LinearLayoutFragment linearLayoutFragment = new LinearLayoutFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.math_frag_container, linearLayoutFragment);
            fragmentTransaction.commit();
*/
            mathfragment = new LinearLayoutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, mathfragment);
            fragmentTransaction.commit();

            tableVisible = false ;

            for(int i = chatHistory.size()-1; i >= 0; i--) {
                temp = username + ": " + chatHistory.get(i) + "\n";
                s.append(temp);
            }

            ((TextView)findViewById(R.id.textView_chatHist)).setText(s);
            ((EditText)findViewById(R.id.editText_chatMsg)).setText("");
        }
        else {
            for(int i = chatHistory.size()-1; i >= 0; i--) {
                temp = username + ": " + chatHistory.get(i) + "\n";
                s.append(temp);
            }

            ((TextView)findViewById(R.id.textView_chatHist)).setText(s);
            ((EditText)findViewById(R.id.editText_chatMsg)).setText("");
        }


    }

    public void generateRandomImg() {
        TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);
        Random random = new Random();
        Log.e("cards", cards.length()+"");
        int randomIndexOne = random.nextInt(52);
        int randomIndexTwo = random.nextInt(52);
        while(randomIndexOne == randomIndexTwo) {
            randomIndexTwo = random.nextInt(52);
        }
        int randomIndexThree = random.nextInt(52);
        while(randomIndexOne == randomIndexThree || randomIndexThree == randomIndexTwo) {
            randomIndexThree = random.nextInt(52);
        }

        int rndImageC1 = cards.getResourceId(randomIndexOne, 0);
        int rndImageC2 = cards.getResourceId(randomIndexTwo, 0);
        int rndImageC3 = cards.getResourceId(randomIndexThree, 0);

        ImageView viewC1 = (ImageView) findViewById(R.id.imageView_card1);
        ImageView viewC2 = (ImageView) findViewById(R.id.imageView_card2);
        ImageView viewC3 = (ImageView) findViewById(R.id.imageView_card3);
        viewC1.setImageResource(rndImageC1);
        viewC2.setImageResource(rndImageC2);
        viewC3.setImageResource(rndImageC3);

    }


    public void generateProblem(){
        int test_answer;
        Random random = new Random();
        String[] operators  = {"-", "+", "*"};
        int randomNum1 = random.nextInt(16);
        int randomNum2 = random.nextInt(16);
        int randomIndex = random.nextInt(3);

        test_answer = calculate(randomNum1, operators[randomIndex], randomNum2);

        while(test_answer < 0){
            randomNum1 = random.nextInt(16);
            randomNum2 = random.nextInt(16);
            randomIndex = random.nextInt(3);
            test_answer = calculate(randomNum1, operators[randomIndex], randomNum2);
        }

        String problem = randomNum1 + " " + operators[randomIndex] + " " + randomNum2 + " = ";
        mathHistory.add(problem + test_answer);
        ((TextView)findViewById(R.id.textView_mathProb)).setText(problem);
        correct_answer = test_answer;
    }

    public int calculate(int num1, String oper, int num2){
        switch(oper) {
            case ("+"): return num1 + num2;
            case ("-"): return num1 - num2;
            case ("*"): return num1 * num2;
            default: return -1;
        }
    }

    public int getCorrectAnswer(){
        return correct_answer;
    }

    public void setPlayerAnswer(int answer){
        player_answer = answer;
    }

    public int getPlayerAnswer(){
        return player_answer;
    }

    public String updateMathHistory(){
        String temp;
        StringBuilder s = new StringBuilder();
        for(int i = mathHistory.size()-1; i >= 0; i--) {
            temp = mathHistory.get(i) + "\n";
            s.append(temp);
        }

        return s.toString();
    }
}


