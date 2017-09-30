package me.henry.scrollads.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class CloudPresUtil {
    public static final String prefres_name = "zengjin";

    public static int getInt(Context context, String tag, int defaultValue) {
        if (context == null)
            return defaultValue;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        return sp.getInt(tag, defaultValue);
    }

    public static int getInt(Context context, String tag) {
        return getInt(context, tag, 0);
    }

    public static void setInt(Context context, String tag, int value) {
        if (context == null)
            return;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(tag, value);
        editor.apply();//editor.commit();
    }
    public static void setInt(Context context, String tag) {
         setInt(context, tag, 0);
    }
    public static float getFloat(Context context, String tag, float defaultValue) {
        if (context == null)
            return defaultValue;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        return sp.getFloat(tag, defaultValue);
    }
    public static float getFloat(Context context, String tag) {
        return getFloat(context, tag, 0);
    }

    public static void setFloat(Context context, String tag, float value) {
        if (context == null)
            return;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(tag, value);
        editor.apply();//editor.commit();
    }
    public static void setFloat(Context context, String tag) {
        setFloat(context, tag, 0);
    }
    public static String getString(Context context, String tag, String defaultValue) {
        if (context == null)
            return "";
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        return sp.getString(tag, defaultValue);
    }
    public static String getString(Context context, String tag) {
        return getString(context, tag, null);
    }

    public static void setString(Context context, String tag, String value) {
        if (context == null)
            return;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(tag, value);
        editor.apply();//editor.commit();
    }
    public static void setString(Context context, String tag) {
        setString(context, tag, null);
    }
    public static boolean getBoolean(Context context, String tag, boolean defaultValue) {
        if (context == null)
            return defaultValue;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        return sp.getBoolean(tag, defaultValue);
    }
    public static boolean getBoolean(Context context, String tag) {
        return getBoolean(context, tag, true);
    }
    public static void setBoolean(Context context, String tag, boolean value) {
        if (context == null)
            return;
        SharedPreferences sp = context.getSharedPreferences(prefres_name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(tag, value);
        editor.apply();
    }
    public static void setBoolean(Context context, String tag) {
        setBoolean(context, tag, true);
    }
}