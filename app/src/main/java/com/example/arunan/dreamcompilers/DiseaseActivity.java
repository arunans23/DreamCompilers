package com.example.arunan.dreamcompilers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class DiseaseActivity extends SingleFragmentActivity {

    //container from Disease Fragment
    //extended from generic class

    private static final String EXTRA_DISEASE_ID = "com.example.arunan.dreamcompilers.disease_id";

    public static Intent newIntent(Context packageContext, UUID diseaseID){
        Intent intent = new Intent(packageContext, DiseaseActivity.class);
        intent.putExtra(EXTRA_DISEASE_ID, diseaseID);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID diseaseId =(UUID) getIntent()
                .getSerializableExtra(EXTRA_DISEASE_ID);
        return DiseaseFragment.newInstance(diseaseId);
    }
}
