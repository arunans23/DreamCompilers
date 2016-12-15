package com.example.arunan.dreamcompilers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.arunan.dreamcompilers.fragments.DiseaseListFragment;

/**
 * Created by arunan on 12/10/16.
 */

public class DiseaseListActivity extends SingleFragmentActivity {
    public static final String EXTRA_USER_EMAIl = "com.example.arunan.dreamcompilers.user_name";

    @Override
    protected Fragment createFragment() {
        String userEmail = (String) getIntent()
                .getSerializableExtra(EXTRA_USER_EMAIl);
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_USER_EMAIl, userEmail);
        Fragment fragment = new DiseaseListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Intent newIntent(Context packageContext, String userEmail){
        Intent intent = new Intent(packageContext, DiseaseListActivity.class);
        intent.putExtra(EXTRA_USER_EMAIl, userEmail);
        return intent;
    }
}
