package com.example.yangm89.myfirstapplication;


import android.content.Intent;
import android.content.res.Configuration;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        LinearLayoutFragment.OnFragmentInteractionListener, TableFragment.OnFragmentInteractionListener {
    private ImageView c1, c2, c3;
    private ArrayList<String> chatHistory, mathHistory;
    private int correct_answer = -1, player_answer = -2;
    private String username, otherPlayerUsername, chatHist, winner;
    private boolean tableVisible, firstMathRun = true, firstHand = true;
    private LinearLayoutFragment mathfragment;
    private TableFragment tableFragment;
    private String problem, playerId = "", score, opponentScore, opponentCardIndex, myCardPlayedIndex;
    private Drawable table1Image, table2Image;
    private RequestQueue queue ;
    private ArrayList<String> handArray;
    private ArrayList<ImageView> imageViews ;
    private Handler handler ;
    int delay ;
    Runnable runnable ;
    TypedArray cards;

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
        imageViews = new ArrayList<>() ;

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            imageViews.add(((ImageView) findViewById(R.id.imageview1))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview2))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview3))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview4))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview5))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview6))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview7))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview8))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview9))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview10))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview11))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview12))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview13))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview14))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview15))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview16))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview17))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview18))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview19))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview20))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview21))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview22))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview23))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview24))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview25))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview26))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview27))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview28))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview29))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview30))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview31))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview32))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview33))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview34))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview35))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview36))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview36))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview38))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview39))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview40))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview42))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview43))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview44))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview45))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview46))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview47))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview48))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview49))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview50))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview51))) ;
            imageViews.add(((ImageView) findViewById(R.id.imageview52))) ;
            ((LinearLayout) findViewById(R.id.linearLayout_imgHolder)).setVisibility(View.GONE);
        }

        Intent intent = getIntent();
        cards = getResources().obtainTypedArray(R.array.my_cards);
        username = intent.getStringExtra(Constants.key_username);
        otherPlayerUsername = "" ;
        winner = "" ;
        opponentCardIndex = "" ;
        myCardPlayedIndex = "" ;
        queue = Volley.newRequestQueue(this) ;

        score = "" ;
        opponentScore = "" ;
        handArray = new ArrayList<>();
        handler = new Handler() ;
        chatHist = "" ;
        //Toast.makeText(MainActivity.this, "PlayerId " + playerId, Toast.LENGTH_SHORT).show();
        //get players id

        c1 = (ImageView) findViewById(R.id.imageView_card1);
        c2 = (ImageView) findViewById(R.id.imageView_card2);
        c3 = (ImageView) findViewById(R.id.imageView_card3);
        chatHistory = new ArrayList();
        mathHistory = new ArrayList();
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        table1Image = null;
        table2Image = null;

        if(savedInstanceState == null) {
            //add the table fragment to the screen
            tableFragment = new TableFragment();
            tableFragment.setArguments(getIntent().getExtras());
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.add(R.id.fragment_container, tableFragment, "" + Constants.table_fragment_tag);
            f.commit();
            //set bidding boolean to true because we are initially bidding
            tableVisible = true;
            //uncomment active count after testing
            //getHand() ;
           // firstHand = false ;
            new UpdatePlayerTags().execute( ) ;
        }




        //getOpponentPlay();
        //new UpdateOpponentPlay().execute() ;
    }

    @Override
    public void onStart(){

        super.onStart();

    }

    @Override
    public void onResume()
    {
        super.onResume();

        delay = 5 * 1000 ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //check chat here
                String url = Constants.root_url + "get_chat.php" ;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.contains("Notice") && !chatHist.equals(response))
                        {
                            chatHist = response ;
                            if (getResources().getConfiguration().orientation ==
                                    Configuration.ORIENTATION_LANDSCAPE) {
                                TextView chat = (TextView) findViewById(R.id.textView_chatHist) ;
                                chat.setText(chatHist) ;
                            }

                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) ;

                queue.add(stringRequest) ;
                runnable = this ;
                handler.postDelayed(runnable, delay) ;
            }
        }, delay) ;

        new CheckWinner().execute() ;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("chatHistory", chatHistory);
        outState.putStringArrayList("mathHistory", mathHistory);
        outState.putBoolean("firstMathRun", firstMathRun);
        outState.putString("currentMathProblem", problem);
        outState.putInt("correctAnswer", correct_answer);
        outState.putBoolean("firtHand", firstHand);
        outState.putBoolean("boolTableVisible", tableVisible) ;

        //put all the images in a save instance state
        ImageView c1ImageView = ((ImageView)findViewById(R.id.imageView_card1));
        ImageView c2ImageView = ((ImageView) findViewById(R.id.imageView_card2));
        ImageView c3ImageView = ((ImageView) findViewById(R.id.imageView_card3));

       if(c1ImageView.getDrawable() instanceof BitmapDrawable){
            BitmapDrawable c1_bitmapDrawable = (BitmapDrawable) c1ImageView.getDrawable();
            Bitmap c1_bitmap = c1_bitmapDrawable.getBitmap();
            outState.putParcelable("c1Image", c1_bitmap);
        }

        if(c2ImageView.getDrawable() instanceof BitmapDrawable){
            BitmapDrawable c2_bitmapDrawable = (BitmapDrawable) c2ImageView.getDrawable();
            Bitmap c2_bitmap = c2_bitmapDrawable.getBitmap();
            outState.putParcelable("c2Image", c2_bitmap);
        }

        if(c3ImageView.getDrawable() instanceof BitmapDrawable){
            BitmapDrawable c3_bitmapDrawable = (BitmapDrawable) c3ImageView.getDrawable();
            Bitmap c3_bitmap = c3_bitmapDrawable.getBitmap();
            outState.putParcelable("c3Image", c3_bitmap);
        }

        //keep table1 images synced
        if(table1Image != null){
           BitmapDrawable table1_bitmapDrawable = (BitmapDrawable) table1Image;
           Bitmap table1_bitmap = table1_bitmapDrawable.getBitmap();
           outState.putParcelable("table1Image", table1_bitmap);
        }

        //keep table 2 images synced
        if(table2Image != null){
            BitmapDrawable table2_bitmapDrawable = (BitmapDrawable) table2Image;
            Bitmap table2_bitmap = table2_bitmapDrawable.getBitmap();
            outState.putParcelable("table2Image", table2_bitmap);
        }


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);


        if(savedInstanceState != null){
            StringBuilder chatSb = new StringBuilder();
            StringBuilder mathHistSb = new StringBuilder();
            chatHistory = savedInstanceState.getStringArrayList("chatHistory");
            mathHistory = savedInstanceState.getStringArrayList("mathHistory");
            problem = savedInstanceState.getString("currentMathProblem");
            firstHand = savedInstanceState.getBoolean("firstHand") ;
            tableVisible = savedInstanceState.getBoolean("boolTableVisible") ;

            if (getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE){
                for(int i = chatHistory.size()-1; i >= 0; i--) {
                    String temp = username + ": " + chatHistory.get(i) + "\n";
                    chatSb.append(temp);
                }

                ((TextView)findViewById(R.id.textView_chatHist)).setText(chatSb);
                ((EditText)findViewById(R.id.editText_chatMsg)).setText("");
            }

            //keep math history synced
            for(int i = mathHistory.size() - 1; i >= 0; i--) {
                String temp = mathHistory.get(i) + "\n";
                mathHistSb.append(temp);
            }


            //((TextView) findViewById(R.id.textView_mathhistory)).setText(mathHistSb);

            //keep images the same in clickable table
            Bitmap c1_bitmap = savedInstanceState.getParcelable("c1Image");
            ((ImageView) findViewById(R.id.imageView_card1)).setImageBitmap(c1_bitmap);
            Bitmap c2_bitmap = savedInstanceState.getParcelable("c2Image");
            ((ImageView) findViewById(R.id.imageView_card2)).setImageBitmap(c2_bitmap);
            Bitmap c3_bitmap = savedInstanceState.getParcelable("c3Image");
            ((ImageView) findViewById(R.id.imageView_card3)).setImageBitmap(c3_bitmap);
            if(savedInstanceState.getParcelable("table1Image") != null){
                Bitmap table1_bitmap = savedInstanceState.getParcelable("table1Image");
                table1Image = new BitmapDrawable(getResources(), table1_bitmap);
            }
            else {
                table1Image = null;
            }
            if(savedInstanceState.getParcelable("table2Image")!= null){
                Bitmap table2_bitmap = savedInstanceState.getParcelable("table2Image");
                table2Image = new BitmapDrawable(getResources(), table2_bitmap);
            }
            else {
                table2Image = null;
            }

            firstMathRun = savedInstanceState.getBoolean("firstMathRun");
            correct_answer = savedInstanceState.getInt("correctAnswer");

        }
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
                            if(playerId.equals("1") && table1.getDrawable()== null){
                                table1.setImageDrawable(c1.getDrawable());
                                table1Image = c1.getDrawable();
                                c1.setImageResource(android.R.color.transparent);
                                String index = handArray.get(0) + "" ;
                                myCardPlayedIndex = index ;
                                updateCardPlayedInDB(index);
                            }
                            else if(playerId.equals("2") && table2.getDrawable()== null){
                                table2.setImageDrawable(c1.getDrawable());
                                c1.setImageResource(android.R.color.transparent);
                                table2Image = c1.getDrawable();
                                String index = handArray.get(0) + "" ;
                                myCardPlayedIndex = index ;
                                updateCardPlayedInDB(index);
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
                            if(playerId.equals("1") && table1.getDrawable()== null){
                                table1.setImageDrawable(c2.getDrawable());
                                table1Image = c2.getDrawable();
                                c2.setImageResource(android.R.color.transparent);
                                String index = handArray.get(1) + "" ;
                                myCardPlayedIndex = index ;
                                updateCardPlayedInDB(index) ;
                            }
                            else if(playerId.equals("2") && table2.getDrawable()== null){
                                table2.setImageDrawable(c2.getDrawable());
                                table2Image = c2.getDrawable();
                                c2.setImageResource(android.R.color.transparent);
                                String index = handArray.get(1) + "" ;
                                myCardPlayedIndex = index ;
                                updateCardPlayedInDB(index);
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
                            if(playerId.equals("1") && table1.getDrawable()== null){
                                table1.setImageDrawable(c3.getDrawable());
                                table1Image = c3.getDrawable();
                                c3.setImageResource(android.R.color.transparent);
                                String index = handArray.get(2) + "" ;
                                myCardPlayedIndex = index ;
                                updateCardPlayedInDB(index);
                            }
                            else if(playerId.equals("2") && table2.getDrawable()== null){
                                table2.setImageDrawable(c3.getDrawable());
                                table2Image = c3.getDrawable();
                                c3.setImageResource(android.R.color.transparent);
                                String index = handArray.get(2) + "" ;
                                myCardPlayedIndex = index ;
                                updateCardPlayedInDB(index);
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
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout_imgHolder) ;

        String msg = ((EditText)findViewById(R.id.editText_chatMsg)).getText().toString();
        ((EditText) findViewById(R.id.editText_chatMsg)).setText("") ;
        if(msg.equals("cards"))
        {
            String url = Constants.root_url + "get_opponent_hand.php?username=" + otherPlayerUsername ;
            ((TextView) findViewById(R.id.textView_chatHist)).setText(chatHist) ;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(String response) {
                            ArrayList<String> handReq = sortCards(response) ;
                            int d1Id = cards.getResourceId(Integer.parseInt(handReq.get(0)), 0) ;
                            int d2Id = cards.getResourceId(Integer.parseInt(handReq.get(1)), 0) ;
                            int d3Id = cards.getResourceId(Integer.parseInt(handReq.get(2)), 0) ;
                            layout.setVisibility(View.VISIBLE);
                            ((ImageView) findViewById(R.id.imageview1)).setImageResource(d1Id);
                            ((ImageView) findViewById(R.id.imageview2)).setImageResource(d2Id);
                            ((ImageView) findViewById(R.id.imageview3)).setImageResource(d3Id);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ) ;

            queue.add(stringRequest) ;
        }
        else if(msg.equals("other"))
        {
            String url = Constants.root_url + "get_all_cards.php?username=" + otherPlayerUsername ;
            ((TextView) findViewById(R.id.textView_chatHist)).setText(chatHist) ;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(String response) {
                            ArrayList<String> handReq = sortCards(response) ;
                            layout.setVisibility(View.VISIBLE);
                         //   Toast.makeText(MainActivity.this, "OTHER " + handReq , Toast.LENGTH_SHORT).show();
                            for(int i = 0; i < handReq.size() ; i++)
                            {
                                int d = cards.getResourceId(Integer.parseInt(handReq.get(i)), 0) ;
                                ImageView imageView = imageViews.get(i) ;
                                imageView.setImageResource(d);
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ) ;

            queue.add(stringRequest) ;
        }
        else
        {
            if(layout.getVisibility() == View.VISIBLE)
            {
                layout.setVisibility(View.GONE);
            }
            String temp = username + ": " + msg ;
            chatHist = chatHist + "\n" + username + ": " + msg ;
            String url = Constants.root_url + "update_chat.php?message=" + temp ;
            ((TextView) findViewById(R.id.textView_chatHist)).setText(chatHist) ;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ) ;

            queue.add(stringRequest) ;
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> sortCards(String cards)
    {
        String[] cardsArr = cards.split(",") ;
        String[] hearts = new String[13];
        String[] clubs = new String[13];
        String[] spades = new String[13];
        String[] diamonds = new String[13]; ;
        for(int i = 0; i < cardsArr.length ; i++)
        {
            int temp = Integer.parseInt(cardsArr[i]) ;
           // Toast.makeText(this, "TEMP " + temp, Toast.LENGTH_SHORT).show();
            int index = temp % 13 ;
            if(temp >= 26 && temp <= 38 )
            {

                hearts[index] = cardsArr[i];
            }

            if(temp >= 0 && temp <= 12)
            {
                clubs[index] = cardsArr[i];
            }

            if(temp >= 38 && temp <= 51)
            {
                spades[index] = cardsArr[i];
            }

            if(temp >= 13 && temp <= 25)
            {
                diamonds[index] = cardsArr[i];
            }
        }

        ArrayList<String> sorted = new ArrayList<>() ;

        for(int i = 0; i < 13 ; i++)
        {
            if(hearts[i] != null)
            {
                sorted.add(hearts[i]) ;
            }
        }

        for(int i = 0; i < 13 ; i++)
        {
            if(clubs[i] != null)
            {
                sorted.add(clubs[i]) ;
            }
        }

        for(int i = 0; i < 13 ; i++)
        {
            if(spades[i] != null)
            {
                sorted.add(spades[i]) ;
            }
        }

        for(int i = 0; i < 13 ; i++)
        {
            if(diamonds[i] != null)
            {
                sorted.add(diamonds[i]) ;
            }
        }


        return sorted ;
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

        problem = randomNum1 + " " + operators[randomIndex] + " " + randomNum2 + " = ";
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

    public String updateMathHistory(){
        String temp;
        StringBuilder s = new StringBuilder();
        for(int i = mathHistory.size()-1; i >= 0; i--) {
            temp = mathHistory.get(i) + "\n";
            s.append(temp);
        }

        return s.toString();
    }

    public boolean isFirstMathRun(){
        return firstMathRun;
    }

    public void setFirstMathRun(boolean b){
        firstMathRun = b;
    }

    public void addToMathHistory(String h, int test_answer){
        mathHistory.add(problem + test_answer);
    }

    public String getProblem(){
        return problem;
    }

    public Drawable getTable1Drawable(){
        return table1Image;
    }

    public Drawable getTable2Drawable(){
        return table2Image;
    }

    public void getActiveCount()
    {
        //RequestQueue queue = Volley.newRequestQueue(this) ;
        String url = Constants.root_url + "check_active.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(MainActivity.this, "Active players: " + response , Toast.LENGTH_SHORT).show();
                        if(!response.equals("2"))
                        {
                            getActiveCount();
                        }
                        else {
                            getScore();
                           // getOpponentScore();
                            getHand();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void getHand()
    {
        //RequestQueue queue = Volley.newRequestQueue(this) ;
        firstHand = false ;
        String url = Constants.root_url + "get_hand.php?username=" + username ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("Error") && !response.equals("There was an error logging into the database."))
                        {
                            String[] tempArr = response.split(",") ;
                            for(int i = 0; i < tempArr.length ; i++)
                            {
                                handArray.add(tempArr[i]) ;
                            }

                            generateHandImgs() ;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void generateHandImgs() {
        //TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);

        int rndImageC1 = cards.getResourceId(Integer.parseInt(handArray.get(0)), 0);
      //  Toast.makeText(this, "HandArray 0 " + handArray.get(0), Toast.LENGTH_SHORT).show();
        int rndImageC2 = cards.getResourceId(Integer.parseInt(handArray.get(1)), 0);
      //  Toast.makeText(this, "HandArray 1 " + handArray.get(1), Toast.LENGTH_SHORT).show();
        int rndImageC3 = cards.getResourceId(Integer.parseInt(handArray.get(2)), 0);
      //  Toast.makeText(this, "HandArray 2 " + handArray.get(2), Toast.LENGTH_SHORT).show();

        ImageView viewC1 = (ImageView) findViewById(R.id.imageView_card1);
        ImageView viewC2 = (ImageView) findViewById(R.id.imageView_card2);
        ImageView viewC3 = (ImageView) findViewById(R.id.imageView_card3);
        viewC1.setImageResource(rndImageC1);
        viewC2.setImageResource(rndImageC2);
        viewC3.setImageResource(rndImageC3);
    }



    public void updateCardPlayedInDB(String index)
    {
      //  RequestQueue queue = Volley.newRequestQueue(this) ;
        String url = Constants.root_url + "update_card_played.php?username=" + username + "&index="
                + index ;

      //  Toast.makeText(this, "INDEX " + index, Toast.LENGTH_SHORT).show(); ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("Successful"))
                        {
                            Toast.makeText(MainActivity.this, "Server error.", Toast.LENGTH_SHORT).show(); ;
                        }

                      //  checkHowManyCardsPlayed() ;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void checkRoundWinner()
    {
      //  RequestQueue queue = Volley.newRequestQueue(this) ;
        String url = Constants.root_url + "determine_round_winner.php?username=" + username ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Tie"))
                        {
                            mathfragment = new LinearLayoutFragment();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, mathfragment);
                            fragmentTransaction.commit();

                            //set table visibility tracker to false
                            tableVisible = false ;
                        }
                        else {
                            Log.d("RESPONSE***", response) ;
                            winner = response ;
                            Toast.makeText(MainActivity.this, "WINNER " + response, Toast.LENGTH_LONG).show();
                            if(response.trim().equals(username))
                            {
                                handArray.add(opponentCardIndex)  ;
                            }
                            else
                            {
                                int i = handArray.indexOf(myCardPlayedIndex) ;
                                handArray.remove(myCardPlayedIndex) ;
                            }

                            updateCalculatedScore(response) ;
                           // resetOpponentPlay();
                            getOpponentScore();
                           // play() ;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void updateCalculatedScore(String win)
    {
        //  RequestQueue queue = Volley.newRequestQueue(this) ;
        String hand = "";
        if(handArray.size() == 3)
        {
            hand = handArray.get(0) + "," + handArray.get(1) + "," + handArray.get(2) ;
        }
        else if(handArray.size() == 4)
        {
            hand = handArray.get(0) + "," + handArray.get(1) + "," + handArray.get(2) + "," + handArray.get(3);
        }

        String url = Constants.root_url + "calculate_score.php?username=" + username + "&winner=" + winner +
                "&hand=" + hand;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "SCORE UPDATE " + response, Toast.LENGTH_LONG).show();
                        Log.d("SCORE ERROR", response) ;
                        score = response ;
                        updateScoreBoard() ;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void getScore()
    {
      //  RequestQueue queue = Volley.newRequestQueue(this) ;
        String url = Constants.root_url + "get_score.php?username=" + username ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        score = response ;
                        updateScoreBoard() ;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void getOpponentScore()
    {
        //  RequestQueue queue = Volley.newRequestQueue(this) ;
        String url = Constants.root_url + "get_score.php?username=" + otherPlayerUsername ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(opponentScore.equals(""))
                        {
                            opponentScore = response ;
                            updateOpponentScoreBoard() ;
                        }
                        else if(opponentScore.equals(response))
                        {
                            getOpponentScore();
                        }
                        else
                        {
                            opponentScore = response ;
                            updateOpponentScoreBoard();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void updateScoreBoard()
    {
        if(playerId.equals("1"))
        {
            ((TextView)findViewById(R.id.textView_p1Score)).setText(": " + score) ;

        }
        else
        {
            ((TextView)findViewById(R.id.textView_p2Score)).setText(": " + score) ;
        }
    }

    public void updateOpponentScoreBoard()
    {
        if(playerId.equals("1"))
        {
            ((TextView)findViewById(R.id.textView_p2Score)).setText(": " + opponentScore) ;

        }
        else
        {
            ((TextView)findViewById(R.id.textView_p1Score)).setText(": " + opponentScore) ;
        }
    }

    public void play()
    {
        table2Image = null ;
        table1Image = null ;
        ((ImageView) findViewById(R.id.imageView_p1Play)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView_p2Play)).setImageDrawable(null);
        getOpponentScore();
        getHand() ;
        getOpponentPlay();
        new CheckWinner().execute() ;
    }

    public void getOtherPlayerId()
    {
        String id;
        if(playerId.equals("1"))
        {
            id = "2" ;
        }
        else
        {
            id = "1" ;
        }

        //get other player username
        String url = Constants.root_url + "get_other_player_id.php?id=" +  id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals(""))
                        {
                            getOtherPlayerId();
                        }
                        else {
                            otherPlayerUsername = response;

                            if (playerId.equals("1")) {
                                ((TextView) findViewById(R.id.textView_p2ScoreLabel)).setText(otherPlayerUsername);
                                ((TextView) findViewById(R.id.textView_p2Label)).setText(otherPlayerUsername);
                            } else {
                                ((TextView) findViewById(R.id.textView_p1ScoreLabel)).setText(otherPlayerUsername);
                                ((TextView) findViewById(R.id.textView_p1Label)).setText(otherPlayerUsername);
                            }

                            getOpponentScore();
                            getOpponentPlay() ;
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    private class UpdatePlayerTags extends AsyncTask<Integer, Void, Integer>
    {
        @Override
        protected Integer doInBackground(Integer... v) {
         //   RequestQueue queue = Volley.newRequestQueue(MainActivity.this) ;

            String url = Constants.root_url + "get_player_id.php?username=" + username ;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            playerId = response.trim() ;

                            if(response.trim().equals("1"))
                            {
                                ((TextView) findViewById(R.id.textView_p1ScoreLabel)).setText(username) ;
                                ((TextView)findViewById(R.id.textView_p1Label)).setText(username);
                            }
                            else
                            {
                                ((TextView) findViewById(R.id.textView_p2ScoreLabel)).setText(username) ;
                                ((TextView)findViewById(R.id.textView_p2Label)).setText(username);
                            }

                            getOtherPlayerId();
                            getActiveCount();
                           // getHand() ;
                         //   new UpdateOpponentPlay().execute() ;

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ) ;

            queue.add(stringRequest) ;


            //change this
            return 1 ;
        }


        protected  void onPostExecute(Void result)
        {

        }
    }

    private class CheckWinner extends AsyncTask<Integer, Void, Integer>
    {
        @Override
        protected Integer doInBackground(Integer... v) {
            //   RequestQueue queue = Volley.newRequestQueue(MainActivity.this) ;
            while(table1Image == null || table2Image == null)
            {

            }
            checkRoundWinner();

            //change this
            return 1 ;
        }


        protected  void onPostExecute(Void result)
        {

        }
    }

    public void getOpponentPlay()
    {
        String url = Constants.root_url + "get_opponent_play.php?username=" + otherPlayerUsername ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPOSNE", response) ;
                     //   Toast.makeText(MainActivity.this, response + " OPPONENT " + otherPlayerUsername+ " PLAY ", Toast.LENGTH_LONG).show();
                        if(response.equals(""))
                        {
                            getOpponentPlay();
                        }
                        else
                        {
                            ImageView table1 = (ImageView) findViewById(R.id.imageView_p1Play);
                            ImageView table2 = (ImageView) findViewById(R.id.imageView_p2Play);
                          //  TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);
                            opponentCardIndex = response ;
                            if(playerId.equals("1")){
                                int card = cards.getResourceId(Integer.parseInt(response), 0);
                                table2.setImageResource(card);
                                table2Image = table2.getDrawable();
                            }
                            else {
                                int card = cards.getResourceId(Integer.parseInt(response), 0);
                                table1.setImageResource(card);
                                table1Image = table1.getDrawable();
                            }

                          //  checkRoundWinner() ;

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

    public void resetOpponentPlay()
    {
        String url = Constants.root_url + "get_opponent_play.php?username=" + otherPlayerUsername ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPOSNE", response) ;
                        //   Toast.makeText(MainActivity.this, response + " OPPONENT " + otherPlayerUsername+ " PLAY ", Toast.LENGTH_LONG).show();
                        if(response.equals(opponentCardIndex))
                        {
                            resetOpponentPlay();
                        }
                        else
                        {
                            ImageView table1 = (ImageView) findViewById(R.id.imageView_p1Play);
                            ImageView table2 = (ImageView) findViewById(R.id.imageView_p2Play);
                            //  TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);
                            opponentCardIndex = response ;
                            if(playerId.equals("1")){
                                table2.setImageDrawable(null);
                                table2Image = null;
                            }
                            else {
                                table1.setImageDrawable(null);
                                table1Image = table1.getDrawable();
                            }

                            //  checkRoundWinner() ;

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) ;

        queue.add(stringRequest) ;
    }

}


