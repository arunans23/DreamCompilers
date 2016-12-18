package com.example.arunan.dreamcompilers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.activities.DiseaseListActivity;
import com.example.arunan.dreamcompilers.models.Disease;
import com.example.arunan.dreamcompilers.models.DiseaseLab;

import java.util.UUID;

import static com.example.arunan.dreamcompilers.R.menu.disease;

/**
 * Created by arunan on 12/9/16.
 */

//fragment class for Disease Log

public class DiseaseFragment extends Fragment {
    private Disease mDisease;
    private TextView mTitleField;
    private EditText mSymptomsField;
    private EditText mDescriptionField;
    private NumberPicker mVictimCount;
    private Spinner mDistrictSpinner;



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
        setHasOptionsMenu(true);
        //new disease gets created when Fragment is created
        UUID diseaseId = (UUID) getArguments().getSerializable(ARG_DISEASE_ID);

        mDisease = DiseaseLab.get(getActivity()).getDisease(diseaseId);
    }

    @Override
    public void onPause() {
        super.onPause();

        DiseaseLab.get(getActivity()).updateDisease(mDisease);
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
                mDisease.setSynced(false);
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
                mDisease.setSynced(false);
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
                mDisease.setSynced(false);
            }
        });

        //mVictimCount.setVisibility(View.INVISIBLE);

        mDistrictSpinner = (Spinner)v.findViewById(R.id.user_district_spinner);



        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(disease, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_disease_save:
                updateDisease();
                Intent intent = DiseaseListActivity
                        .newIntent(getActivity(), mDisease.getUserEmail());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateDisease(){

        mDisease.setLocation(mDistrictSpinner.getSelectedItem().toString());
    }
}
