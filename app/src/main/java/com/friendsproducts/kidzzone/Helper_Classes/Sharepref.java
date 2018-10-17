package com.friendsproducts.kidzzone.Helper_Classes;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ali Hassan on 5/26/2018.
 */

public class Sharepref {
    private static Context context;

    /*Constent*/


    public static final String RANDOM_CODE="randomCode";

    //STUDENT CONSTANTS

    public static final String UNIQUE_ID ="id";
    public static final String STD_USER_NAME="stdUserName";
    public static final String STD_UNIQUE_USER="stdUniqueUser";
    public static final String STD_EMAIL="stdEmail";
    public static final String STD_PASS="stdPassword";
    public static final String STD_GENDER="stdGender";
    public static final String STD_AGE="stdAge";
    public static final String STD_PH="stdPhNmbr";
    public static final String STD_IMAGE="stdimage";
    public static final String TECH_EMAIL="tecEmail";
    public static final String TECH_UNIQUE_NAME="tecUniqueName";
    public static final String REQ_STATUS="reqStatus";

    //TEACHER CONSTANTS
    public static final String TEC_NAME = "tecName";
    public static final String TEC_UNIQUE_NAME = "tecUniqueName";
    public static final String TEC_EMAIL = "tecEmail";
    public static final String TEC_PASS = "tecPass";
    public static final String TEC_QUALI = "tecQual";
    public static final String TEC_NMBR = "tecNmbr";
    public static final String TEC_GENDER = "tecGender";
    public static final String TEC_IMAGE = "tecUrl";


    public static final String WHO = "who";

    /*Constent End*/

    public Sharepref(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "MyPrefs";

    public static void setInt( String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"");
    }

    public static void setBool(String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key,false);
    }

    public static void clearPref()
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

}
