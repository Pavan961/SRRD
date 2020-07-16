package com.example.srrd;

/**
 * Created by kapil on 20/01/17.
 */
import android.content.Context;
import android.content.SharedPreferences;


public class DarkModePreferenceManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "srrd-dark-mode";

    private static final String IS_NIGHT_MODE = "IsNightMode";


    public DarkModePreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDarkMode(boolean isFirstTime) {
        editor.putBoolean(IS_NIGHT_MODE, isFirstTime);
        editor.commit();
    }

    public boolean isNightMode() {
        return pref.getBoolean(IS_NIGHT_MODE, true);
    }

}