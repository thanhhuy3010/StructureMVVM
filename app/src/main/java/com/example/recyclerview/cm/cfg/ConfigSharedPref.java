//********************************************************************************************
/**
 * @file    ConfigManager.java
 * @brief   Implement functions to manage configuration feature
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.cm.cfg;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Class ConfigSharedPref has responsibility to setup, edit and get stored data of application
 */
class ConfigSharedPref {
  /**
   * Tag for logger
   */
  private static final String TAG = "CFG_PREF";

  /**
   * SharedPreference file name
   */
  private static final String PREF_NAME = "SSSU_Pref";

  /**
   * Instance of SSSUPreference
   */
  private static ConfigSharedPref configSharedPref;

  /**
   * Instance of SharedPreferences
   */
  private SharedPreferences sharedPreferences;

  /**
   * Instance of SharedPreferences.Editor
   */
  private SharedPreferences.Editor editor;

  /**
   * Get instance of SSSUPreference; create a new one if it's null
   * @param   context  Application Context instance
   * @return  instance of SSSUPreference
   */
  public static ConfigSharedPref getInstance(Context context) {
    Log.d(TAG, "getInstance");
    if (configSharedPref == null) {
      configSharedPref = new ConfigSharedPref(context);
    }
    return configSharedPref;
  }

  /**
   * Create SSSUPreference object which retrieved SharedPreferences and its Editor
   * @param context    Application Context instance
   */
  private ConfigSharedPref(Context context) {
    Log.d(TAG, "Constructor");
    sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();
  }

  /**
   * Check if configuration file contains setting data or not
   * @return  true if number of setting items is greater than 0; otherwise return false
   */
  boolean isConfigAvailable() {
    if (sharedPreferences.getAll().size() == 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Save text data into SharedPreferences
   * @param key   key of data
   * @param value value of data in String
   */
   void saveStringData(String key, String value) {
    Log.d(TAG, "saveStringData: [" + key + "] = " + value);
    editor.putString(key, value);
    editor.apply();
  }

  /**
   * Save integer data into SharedPreferences
   * @param key   key of data
   * @param value value of data in integer
   */
  void saveIntData(String key, int value) {
    Log.d(TAG, "saveIntData: [" + key + "] = " + value);
    editor.putInt(key, value);
    editor.apply();
  }

  /**
   * Get text data from SharedPreferences by key
   * @param   key   key of data
   * @return  the corresponding data in String
   */
   String getStringData(String key) {
    String value = sharedPreferences.getString(key, "");
    Log.d(TAG, "getStringData from ["+ key + "] = " + value);
    return value;
  }

  /**
   * Get integer data from SharedPreferences by key
   * @param   key   key of data
   * @return  the corresponding data in integer
   */
   int getIntData(String key) {
    int value = sharedPreferences.getInt(key, -1);
    Log.d(TAG, "getIntData from ["+ key + "] = " + value);
    return value;
  }

  boolean isContainKey(String key) {
     boolean isContain = sharedPreferences.contains(key);
     Log.d(TAG, "isContainKey  ["+ key + "] = " + isContain);
     return isContain;
  }

}
