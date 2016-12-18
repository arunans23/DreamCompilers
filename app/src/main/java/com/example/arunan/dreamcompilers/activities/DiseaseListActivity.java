package com.example.arunan.dreamcompilers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.fragments.DiseaseListFragment;

/**
 * Created by arunan on 12/10/16.
 */

public class DiseaseListActivity extends AppCompatActivity {
    public static final String EXTRA_USER_EMAIl = "com.example.arunan.dreamcompilers.user_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment==null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

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
