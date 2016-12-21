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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.activities.DiseaseListActivity;
import com.example.arunan.dreamcompilers.app.AppConfig;
import com.example.arunan.dreamcompilers.models.Disease;
import com.example.arunan.dreamcompilers.models.DiseaseLab;
import com.example.arunan.dreamcompilers.models.UserInfo;
import com.example.arunan.dreamcompilers.models.UserLab;

import static com.example.arunan.dreamcompilers.R.menu.disease;

/**
 * Created by arunan on 12/9/16.
 */

//fragment class for Disease Log

public class DiseaseFragment extends Fragment {
    private Disease mDisease;
    private AutoCompleteTextView mTitleField;
    private EditText mSymptomsField;
    private EditText mDescriptionField;
    private NumberPicker mVictimCount;
    private Spinner mDistrictSpinner;

    private UserInfo currentUser;
    private UserLab mUserLab;

    private String[] diseaseOptions = {"Avian influenza",
            "Cholera",
            "Coronaviruses (MERS-CoV, SARS)",
            "Ebola virus disease",
            "Hendra virus infection",
            "Influenza (seasonal, pandemic)",
            "Leptospirosis",
            "Malaria",
            "Meningitis",
            "Nipah virus infection",
            "Plague",
            "Rift Valley fever",
            "Smallpox and human monkeypox",
            "Tularaemia",
            "Ebola, Marburg, Lassa, Crimean-Congo haemorrhagic fever",
            "Yellow fever",
            "Zika virus"};


    private static final String ARG_DISEASE_ID = "disease_id";

    public static DiseaseFragment newInstance(String diseaseID){
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
        String diseaseId = (String) getArguments().getSerializable(ARG_DISEASE_ID);

        mDisease = DiseaseLab.get(getActivity()).getDisease(diseaseId);
        mUserLab = UserLab.get(getActivity());
        currentUser = mUserLab.getUser(mDisease.getUserName());
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
        mTitleField = (AutoCompleteTextView)v.findViewById(R.id.disease_title);
        ArrayAdapter adapter = new
                ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, diseaseOptions);
        mTitleField.setAdapter(adapter);
        mTitleField.setThreshold(3);
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

        if (!currentUser.getRoleId().equals(AppConfig.ROLE_APP_USER)){
            mVictimCount.setVisibility(View.VISIBLE);
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
        }else{
            mVictimCount.setValue(1);
            mVictimCount.setVisibility(View.INVISIBLE);
        }


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
                if (!mTitleField.getText().toString().isEmpty()){
                    updateDisease();
                    Intent intent = DiseaseListActivity
                            .newIntent(getActivity(), mDisease.getUserName());
                    startActivity(intent);
                    return true;
                }else{
                    Toast.makeText(getActivity(), "Enter details", Toast.LENGTH_LONG).show();
                    return true;
                }


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateDisease(){

        mDisease.setLocation(mDistrictSpinner.getSelectedItem().toString());
        mDisease.setTitle(mTitleField.getText().toString());
        mDisease.setSynced(false);
    }
}
