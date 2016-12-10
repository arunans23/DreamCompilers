package com.example.arunan.dreamcompilers;

import android.support.v4.app.Fragment;

public class DiseaseActivity extends SingleFragmentActivity {

    //container from Disease Fragment
    //extended from generic class


    @Override
    protected Fragment createFragment() {
        return new DiseaseFragment();
    }
}
