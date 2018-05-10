package com.example.yangm89.myfirstapplication;

import android.content.Context;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class LinearLayoutFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private String problem ;
    private int mathProblemCount ;

    public LinearLayoutFragment() {
        //required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math, container, false);
    }

    public void onResume() {
        super.onResume();

        mathProblemCount = 0 ;
        problem = mListener.getProblem(mathProblemCount);
        ((TextView)getActivity().findViewById(R.id.textView_mathProb)).setText(problem);



        ((ImageView) getActivity().findViewById(R.id.imageView_card3)).setOnClickListener(null);
        ((ImageView) getActivity().findViewById(R.id.imageView_card2)).setOnClickListener(null);
        ((ImageView) getActivity().findViewById(R.id.imageView_card1)).setOnClickListener(null);

        final Button mathButton = ((Button) getActivity().findViewById(R.id.button_mathSend));
        mathButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(!((EditText) getActivity().findViewById(R.id.editText_answer)).getText().toString().equals("")){
                    int player_answer = Integer.parseInt(((EditText)getActivity().findViewById(R.id.editText_answer)).getText().toString());
                    ((EditText)getActivity().findViewById(R.id.editText_answer)).setText("");
                    if(player_answer == mListener.getCorrectAnswer(mathProblemCount)){
                        String math = mListener.updateMathHistory();
                       ((TextView) getActivity().findViewById(R.id.textView_mathhistory)).setText(math);
                        mListener.setPlayerAnswer(player_answer);
                        if(mathProblemCount < 3) {
                            mathProblemCount++;
                            problem = mListener.getProblem(mathProblemCount);
                            ((TextView) getActivity().findViewById(R.id.textView_mathProb)).setText(problem);
                        }
                    }
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
        int getCorrectAnswer(int i);
        void setPlayerAnswer(int answer);
        String updateMathHistory();
        boolean isFirstMathRun();
        void setFirstMathRun(boolean b);
        void addToMathHistory(String h, int test_answer);
        String getProblem(int i);
    }






}
