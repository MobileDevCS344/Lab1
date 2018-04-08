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

import java.util.Random;


public class LinearLayoutFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

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
        mListener.generateProblem();

        ((ImageView) getActivity().findViewById(R.id.imageView_card3)).setOnClickListener(null);
        ((ImageView) getActivity().findViewById(R.id.imageView_card2)).setOnClickListener(null);
        ((ImageView) getActivity().findViewById(R.id.imageView_card1)).setOnClickListener(null);

        final Button mathButton = ((Button) getActivity().findViewById(R.id.button_mathSend));
        mathButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int player_answer = Integer.parseInt(((EditText)getActivity().findViewById(R.id.editText_answer)).getText().toString());
                ((EditText)getActivity().findViewById(R.id.editText_answer)).setText("");
                if(player_answer == mListener.getCorrectAnswer()){
                    String math = mListener.updateMathHistory();
                    ((TextView) getActivity().findViewById(R.id.textView_mathhistory)).setText(math);
                    mListener.setPlayerAnswer(player_answer);
                    mListener.generateProblem();
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
        void generateProblem();
        int getCorrectAnswer();
        void setPlayerAnswer(int answer);
        int getPlayerAnswer();
        String updateMathHistory();
    }






}
