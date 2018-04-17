package com.example.yangm89.myfirstapplication;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class TableFragment extends Fragment {
    private TableFragment.OnFragmentInteractionListener mListener;

    public TableFragment() {
        //required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    public void onResume() {
        super.onResume();

        Drawable table1Image = mListener.getTable1Drawable();
        if(table1Image != null){
            ((ImageView) getActivity().findViewById(R.id.imageView_p1Play)).setImageDrawable(table1Image);
        }
        Drawable table2Image = mListener.getTable2Drawable();
        if(table2Image != null ){
            ((ImageView) getActivity().findViewById(R.id.imageView_p2Play)).setImageDrawable(table2Image);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TableFragment.OnFragmentInteractionListener) {
            mListener = (TableFragment.OnFragmentInteractionListener) context;
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
        Drawable getTable1Drawable();
        Drawable getTable2Drawable();
    }
}
