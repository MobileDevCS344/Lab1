package com.example.yangm89.myfirstapplication;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int[] cards = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
                    R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.cj,
                    R.drawable.cq, R.drawable.ck, R.drawable.ct,
                    R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
                    R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.dj,
                    R.drawable.dq, R.drawable.dk, R.drawable.dt,
                    R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
                    R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.hj,
                    R.drawable.hq, R.drawable.hk, R.drawable.ht,
                    R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                    R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.sj,
                    R.drawable.sq, R.drawable.sk, R.drawable.st};
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

        Random r = new Random();
        int randomIndexOne = r.nextInt(53);
        int randomIndexTwo = r.nextInt(53);
        while(randomIndexTwo == randomIndexOne){
            randomIndexTwo = r.nextInt(53);
        }
        int randomIndexThree = r.nextInt(53);
        while(randomIndexOne == randomIndexThree || randomIndexTwo == randomIndexThree){
            randomIndexThree = r.nextInt(53);
        }
        ImageView viewC1 = (ImageView) findViewById(R.id.imageView_card1);
        ImageView viewC2 = (ImageView) findViewById(R.id.imageView_card2);
        ImageView viewC3 = (ImageView) findViewById(R.id.imageView_card3);
        viewC1.setImageResource(cards[randomIndexOne]);
        viewC2.setImageResource(cards[randomIndexTwo]);
        viewC3.setImageResource(cards[randomIndexThree]);
    }

    public void changeBackground(View view){
        ImageView imageView = (ImageView) view;
        imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBackgroundSkyBlue));
    }
}
