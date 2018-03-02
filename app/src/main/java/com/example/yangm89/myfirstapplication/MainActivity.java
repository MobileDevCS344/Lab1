package com.example.yangm89.myfirstapplication;

import android.content.res.Configuration;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView c1, c2, c3;


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

        //random image generator
        generateRandomImg();

        c1 = (ImageView) findViewById(R.id.imageView_card1);
        c2 = (ImageView) findViewById(R.id.imageView_card2);
        c3 = (ImageView) findViewById(R.id.imageView_card3);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageView_card1:
                if(((ColorDrawable)c1.getBackground()) != null ) {
                    if(((ColorDrawable)c1.getBackground()).getColor() == 0){
                        c1.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                        if(((ColorDrawable)c2.getBackground()) != null) {
                            c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                        if(((ColorDrawable)c3.getBackground()) != null) {
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
                    if(((ColorDrawable)c2.getBackground()) != null) {
                        c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                    if(((ColorDrawable)c3.getBackground()) != null) {
                        c3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                }

                break;

            case R.id.imageView_card2:
                if(((ColorDrawable)c2.getBackground()) != null ) {
                    if(((ColorDrawable)c2.getBackground()).getColor() == 0){
                        c2.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                        if(((ColorDrawable)c1.getBackground()) != null) {
                            c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                        if(((ColorDrawable)c3.getBackground()) != null) {
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
                    if(((ColorDrawable)c1.getBackground()) != null) {
                        c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                    if(((ColorDrawable)c3.getBackground()) != null) {
                        c3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                }

                break;

            case R.id.imageView_card3:
                if(((ColorDrawable)c3.getBackground()) != null ) {
                    if(((ColorDrawable)c3.getBackground()).getColor() == 0){
                        c3.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
                        if(((ColorDrawable)c1.getBackground()) != null) {
                            c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                        }
                        if(((ColorDrawable)c2.getBackground()) != null) {
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
                    if(((ColorDrawable)c1.getBackground()) != null) {
                        c1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                    if(((ColorDrawable)c2.getBackground()) != null) {
                        c2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    }
                }

                break;
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
        while(randomIndexOne == randomIndexThree || randomIndexTwo == randomIndexOne) {
            randomIndexThree = random.nextInt(52);
        }

        int rndImageC1 = cards.getResourceId(randomIndexOne, 0);
        int rndImageC2 = cards.getResourceId(randomIndexTwo, 0);
        int rndImageC3 = cards.getResourceId(randomIndexThree, 0);

        Log.d("rnd1", randomIndexOne+"");
        Log.d("rnd2", randomIndexTwo+"");
        Log.d("rnd3", randomIndexThree+"");
        ImageView viewC1 = (ImageView) findViewById(R.id.imageView_card1);
        ImageView viewC2 = (ImageView) findViewById(R.id.imageView_card2);
        ImageView viewC3 = (ImageView) findViewById(R.id.imageView_card3);
        viewC1.setImageResource(rndImageC1);
        viewC2.setImageResource(rndImageC2);
        viewC3.setImageResource(rndImageC3);



    }
}
