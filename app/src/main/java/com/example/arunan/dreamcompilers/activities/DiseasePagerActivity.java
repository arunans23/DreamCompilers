package com.example.arunan.dreamcompilers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.fragments.DiseaseFragment;
import com.example.arunan.dreamcompilers.models.Disease;
import com.example.arunan.dreamcompilers.models.DiseaseLab;

import java.util.List;

/**
 * Created by arunan on 12/10/16.
 */

//class to implement ViewPager in Disease fragments

public class DiseasePagerActivity extends AppCompatActivity {

    private static final String EXTRA_DISEASE_ID = "com.example.arunan.dreamcompilers.disease_id";

    private ViewPager mViewPager;
    private List<Disease> mDiseases;

    public static Intent newIntent(Context packageContext, String diseaseId){
        Intent intent = new Intent(packageContext, DiseasePagerActivity.class);
        intent.putExtra(EXTRA_DISEASE_ID, diseaseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_pager);

        String diseaseId = (String) getIntent()
                .getSerializableExtra(EXTRA_DISEASE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_disease_view_pager);

        mDiseases = DiseaseLab.get(this).getDiseases();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Disease disease = mDiseases.get(position);
                return DiseaseFragment.newInstance(disease.getEntryId());
            }

            @Override
            public int getCount() {
                return mDiseases.size();
            }
        });

        for (int i = 0; i < mDiseases.size(); i++){
            if (mDiseases.get(i).getEntryId().equals(diseaseId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
