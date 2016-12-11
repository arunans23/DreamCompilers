package com.example.arunan.dreamcompilers.activities;

import android.support.v4.app.Fragment;

import com.example.arunan.dreamcompilers.fragments.DiseaseListFragment;

/**
 * Created by arunan on 12/10/16.
 */

public class DiseaseListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DiseaseListFragment();
    }
}
