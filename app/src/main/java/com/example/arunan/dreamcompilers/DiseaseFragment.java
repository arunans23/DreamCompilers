package com.example.arunan.dreamcompilers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

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
    private NumberPicker mVictimCount;

    private static final String ARG_DISEASE_ID = "disease_id";

    public static DiseaseFragment newInstance(UUID diseaseID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DISEASE_ID, diseaseID);

        DiseaseFragment fragment = new DiseaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //new disease gets created when Fragment is created
        UUID diseaseId = (UUID) getArguments().getSerializable(ARG_DISEASE_ID);

        mDisease = DiseaseLab.get(getActivity()).getDisease(diseaseId);
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
        mSymptomsField.setText(mDisease.getSymptoms());
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
        mDescriptionField.setText(mDisease.getDescription());
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

        //VictimCount field
        mVictimCount = (NumberPicker)v.findViewById(R.id.disease_victim_count);
        mVictimCount.setMinValue(0);
        mVictimCount.setMaxValue(10000);
        mVictimCount.setValue(mDisease.getNoVictims());
        mVictimCount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mDisease.setNoVictims(newVal);
                mDisease.setLastEditDate(new Date());
            }
        });




        return v;
    }
}
