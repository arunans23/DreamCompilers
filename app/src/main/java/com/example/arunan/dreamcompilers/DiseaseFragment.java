package com.example.arunan.dreamcompilers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by arunan on 12/9/16.
 */

public class DiseaseFragment extends Fragment {
    private Disease mDisease;
    private TextView mTitleField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisease = new Disease();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_disease, container, false);

        mTitleField = (TextView)v.findViewById(R.id.disease_title);
        mTitleField.setText(mDisease.getTitle());
        return v;
    }
}
