package com.example.arunan.dreamcompilers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by arunan on 12/9/16.
 */

//fragment class for Disease Log

public class DiseaseFragment extends Fragment {
    private Disease mDisease;
    private TextView mTitleField;
    private EditText mSymptomsField;
    private EditText mDescriptionField;
    private EditText mNoVictims;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //new disease gets created when Fragment is created
        mDisease = new Disease();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_disease, container, false);



        //Title field
        mTitleField = (TextView)v.findViewById(R.id.disease_title);
        mTitleField.setText(mDisease.getTitle());



        //Symptoms Field
        mSymptomsField = (EditText)v.findViewById(R.id.disease_symptoms);
        mSymptomsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDisease.setSymptoms(s.toString());
                mDisease.setLastEditDate(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //Description field
        mDescriptionField = (EditText)v.findViewById(R.id.disease_description);
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDisease.setDescription(s.toString());
                mDisease.setLastEditDate(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        return v;
    }
}
