package com.example.arunan.dreamcompilers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arunan on 12/10/16.
 */

//Fragment class for Disease list

public class DiseaseListFragment extends Fragment {
    private RecyclerView mDiseaseRecylcerView;
    private DiseaseAdapter mAdapter;

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

    //updateUI method for configuring user interfaces
    private void updateUI(){
        DiseaseLab diseaseLab = DiseaseLab.get(getActivity());
        List<Disease> diseases = diseaseLab.getDiseases();

        mAdapter = new DiseaseAdapter(diseases);
        mDiseaseRecylcerView.setAdapter(mAdapter);
    }


    //view holder class
    private class DiseaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;

        private Disease mDisease;

        public DiseaseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_disease_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_disease_date_text_view);
        }

        public void bindDisease(Disease disease){
            mDisease = disease;
            mTitleTextView.setText(mDisease.getTitle());
            mDateTextView.setText(mDisease.getDate().toString());
        }

        @Override
        public void onClick(View v) {

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
    }
}

