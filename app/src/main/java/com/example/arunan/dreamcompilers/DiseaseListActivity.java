package com.example.arunan.dreamcompilers;

import android.support.v4.app.Fragment;

/**
 * Created by arunan on 12/10/16.
 */

public class DiseaseListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DiseaseListFragment();
    }
}
