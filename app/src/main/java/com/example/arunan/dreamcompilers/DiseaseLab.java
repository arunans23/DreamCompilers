package com.example.arunan.dreamcompilers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by arunan on 12/10/16.
 */


//Singleton class to store disease log list

public class DiseaseLab {
    private static DiseaseLab sDiseaseLab;

    private List<Disease> mDiseases;

    public static DiseaseLab get(Context context){
        if (sDiseaseLab == null){
            sDiseaseLab = new DiseaseLab(context);
        }
        return sDiseaseLab;
    }

    private DiseaseLab(Context context){
        mDiseases = new ArrayList<>();
//        Disease sample = new Disease();
//        sample.setDescription("this disease description is sample");
//        sample.setSymptoms("this disease symptoms is sample");
//        sample.setNoVictims(2);
//
//        mDiseases.add(sample);
    }

    public List<Disease> getDiseases(){
        return mDiseases;
    }

    public Disease getDisease(UUID id){
        for (Disease disease : mDiseases){
            if (disease.getEntryId().equals(id)){
                return disease;
            }
        }
        return null;
    }

    public void addDisease(Disease disease){
        mDiseases.add(disease);
    }
}
