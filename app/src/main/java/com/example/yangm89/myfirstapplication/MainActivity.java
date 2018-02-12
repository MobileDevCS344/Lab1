package com.example.yangm89.myfirstapplication;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
        } else{
            setContentView(R.layout.activity_portrait);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //initialize random card during start up
        String[] sArray = {"c", "d", "h", "s"};
        String card1, card2, card3;
        Random random = new Random();
        int rIndex = random.nextInt(4 - 0) + 0;
        int rCard = random.nextInt(27 - 1) + 1;
        card1 = new String(sArray[rIndex] + rCard);
        ImageView c1Image = (ImageView) (findViewById(R.id.imageView_card1));
        rIndex = random.nextInt(4 - 0) + 0;
        rCard = random.nextInt(27 - 1) + 1;
        card2 = new String(sArray[rIndex] + rCard);
        while(card2.equals(card1)){
            rIndex = random.nextInt(4 - 0) + 0;
            rCard = random.nextInt(27 - 1) + 1;
            card2 = sArray[rIndex] + rCard;
        }
        ImageView c2Image = (ImageView) (findViewById(R.id.imageView_card2));
        rIndex = random.nextInt(4 - 0) + 0;
        rCard = random.nextInt(27 - 1) + 1;
        card3 = new String(sArray[rIndex] + rCard);
        while(card3.equals(card2) || card3.equals(card1)){
            rIndex = random.nextInt(4 - 0) + 0;
            rCard = random.nextInt(27 - 1) + 1;
            card3 = sArray[rIndex] + rCard;
        }
        ImageView c3Image = (ImageView) (findViewById(R.id.imageView_card2));
    }
}
