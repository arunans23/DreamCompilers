package com.example.arunan.dreamcompilers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.activities.DiseaseListActivity;
import com.example.arunan.dreamcompilers.activities.DiseasePagerActivity;
import com.example.arunan.dreamcompilers.activities.LoginActivity;
import com.example.arunan.dreamcompilers.models.Disease;
import com.example.arunan.dreamcompilers.models.DiseaseLab;
import com.example.arunan.dreamcompilers.models.UserInfo;
import com.example.arunan.dreamcompilers.models.UserLab;

import java.util.List;

/**
 * Created by arunan on 12/10/16.
 */

//Fragment class for Disease list

public class DiseaseListFragment extends Fragment {
    private RecyclerView mDiseaseRecylcerView;
    private DiseaseAdapter mAdapter;
    private String userEmail;
    private UserLab mUserLab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        userEmail = getArguments().getString(DiseaseListActivity.EXTRA_USER_EMAIl);
        mUserLab = UserLab.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease_list, container, false);
        mDiseaseRecylcerView = (RecyclerView) view
                .findViewById(R.id.disease_recycler_view);
        mDiseaseRecylcerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //updateUI method for configuring user interfaces
    private void updateUI(){
        DiseaseLab diseaseLab = DiseaseLab.get(getActivity());
        List<Disease> diseases = diseaseLab.getUserDiseases(userEmail);

        if (mAdapter == null){
            mAdapter = new DiseaseAdapter(diseases);
            mDiseaseRecylcerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDiseases(diseases);
            mAdapter.notifyDataSetChanged();
        }
    }

    //inflate menu options
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_disease_list, menu);
    }

    //actions regarding selecting menu options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_disease:
                Disease disease = new Disease(userEmail);
                DiseaseLab.get(getActivity()).addDisease(disease);
                Intent intent = DiseasePagerActivity
                        .newIntent(getActivity(), disease.getEntryId());
                startActivity(intent);
                return true;
            case R.id.menu_item_logout:
                UserInfo userInfo = mUserLab.getUser(userEmail);
                userInfo.setLogged(false);
                mUserLab.updateUser(userInfo);
                Intent intentLog = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(intentLog);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //view holder class
    private class DiseaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mCheckBox;


        private Disease mDisease;

        public DiseaseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_disease_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_disease_date_text_view);
            mCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_checkbox);

        }

        public void bindDisease(Disease disease){
            mDisease = disease;
            mTitleTextView.setText(mDisease.getTitle());
            mDateTextView.setText(mDisease.getLocation());
            mCheckBox.setChecked(mDisease.isSynced());
        }

        //start Disease activity when clicked
        @Override
        public void onClick(View v) {
            Intent intent = DiseasePagerActivity.newIntent(getActivity(), mDisease.getEntryId());
            startActivity(intent);
        }
    }

    //recycler view class
    private class DiseaseAdapter extends RecyclerView.Adapter<DiseaseHolder> {
        private List<Disease> mDiseases;


        public DiseaseAdapter(List<Disease> diseases) {
            mDiseases = diseases;
        }

        @Override
        public DiseaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_disease, parent, false);
            return new DiseaseHolder(view);
        }

        @Override
        public void onBindViewHolder(DiseaseHolder holder, int position) {
            Disease disease = mDiseases.get(position);
            holder.bindDisease(disease);
        }

        @Override
        public int getItemCount() {
            return mDiseases.size();
        }

        public void setDiseases(List<Disease> diseases){
            mDiseases = diseases;
        }
    }
}

