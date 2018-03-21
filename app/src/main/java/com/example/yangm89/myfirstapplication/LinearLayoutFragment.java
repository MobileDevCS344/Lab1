package com.example.yangm89.myfirstapplication;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class LinearLayoutFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private int correct_answer;

    public LinearLayoutFragment() {
        //required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math, container, false);
    }

    public void onResume() {
        super.onResume();
        generate_problem();

        final Button mathButton = ((Button) getActivity().findViewById(R.id.button_mathSend));
        mathButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int player_answer = Integer.parseInt(((EditText)getActivity().findViewById(R.id.editText_answer)).getText().toString());
                Log.d("p answer", player_answer+"");
                Log.d("correct a", correct_answer+"");
                ((EditText)getActivity().findViewById(R.id.editText_answer)).setText("");
                if(player_answer == correct_answer){
                    generate_problem();
                }
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        //3 - Ensure the activity implements this activity
        void update_score();
    }


    public int calculate(int num1, String oper, int num2){
        switch(oper) {
            case ("+"): return num1 + num2;
            case ("-"): return num1 - num2;
            case ("*"): return num1 * num2;
            default: return -1;
        }
    }

    public void generate_problem(){
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
        ((TextView)getActivity().findViewById(R.id.textView_mathProb)).setText(problem);
        Log.d("num 1", randomNum1+"");
        Log.d("num 2", randomNum2 +" ");
        correct_answer = test_answer;
    }


}
