//********************************************************************************************
/**
 * @file    ConfigManager.java
 * @brief   Implement functions to save and retrieve configuration values of application
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.cm.cfg;

import android.content.Context;
import android.util.Log;

/**
 * Class to save and retrieve configuration values of application
 */
public class ConfigManager {
  /**
   * Tag for logger
   */
  private static final String TAG = "CFG_MGR";

  /**
   * Instance of ConfigManager
   */
  private static ConfigManager configMgr;

  /**
   * SSSUPreference instance
   */
  private static ConfigSharedPref configSharedPref;

  /**
   * Get instance of ConfigManager; create a new one if it's null
   * @param   context  Application Context instance
   * @return  instance of ConfigManager
   */
  public static ConfigManager getInstance(Context context) {
    Log.d(TAG, "getInstance");
    if (configMgr == null) {
      configMgr = new ConfigManager(context);
    }
    return configMgr;
  }

  /**
   * Constructor
   * @param context   Context instance
   */
  private ConfigManager(Context context) {
    Log.d(TAG, "Constructor");
    configSharedPref = ConfigSharedPref.getInstance(context);
  }

  /**
   * Check and set missing config items
   */
  public void setupConfigData() {
    if (!configSharedPref.isConfigAvailable()) {
      Log.d(TAG, "setupConfigData: Empty config file.");
      setupAllItems();
    } else if (!configSharedPref.isContainKey(ConfigConstants.LIFE_CHECK_PERIOD_KEY)) {
      Log.d(TAG, "setupConfigData: No LIFE_CHECK_PERIOD_KEY.");
      setLifeCheckPeriod();
    }
    // TODO: Check and set other setting items
  }

  /**
   *  Setup all data if config file has no setting data
   */
  public void setupAllItems() {
    Log.d(TAG, "setupAllItems.");
    setLifeCheckPeriod();
    // TODO: Store all setting items if they are not available
  }

  /**
   * Get LifeCheckPeriod value stored in SharedPreference
   * @return  LifeCheckPeriod value
   */
  public static int getLifeCheckPeriod() {
    Log.d(TAG, "getLifeCheckPeriod.");
    return configSharedPref.getIntData(ConfigConstants.LIFE_CHECK_PERIOD_KEY);
  }

  /**
   * Set LifeCheckPeriod value to store in Shared Preference
   */
  private void setLifeCheckPeriod() {
    Log.d(TAG, "setLifeCheckPeriod");
    configSharedPref.saveIntData(ConfigConstants.LIFE_CHECK_PERIOD_KEY,
      ConfigConstants.LIFE_CHECK_PERIOD_VALUE);
  }
}
