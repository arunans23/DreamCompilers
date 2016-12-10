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
}
