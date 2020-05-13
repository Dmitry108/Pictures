package ru.bdim.pictures.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class DatePreference {

    private static final String FILE = "file";
    private static final String DATE = "date";
    private SharedPreferences preferences;

    public DatePreference(Context context){
        if (context != null){
            preferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        }
    }
    public String getSavedDate(){
        return preferences.getString(DATE,(new Date(0L)).toString());
    }
    public void saveDate(Date date){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DATE, date.toString()).apply();
    }
}
