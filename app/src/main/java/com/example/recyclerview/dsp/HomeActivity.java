//********************************************************************************************
/**
 * @file    HomeActivity.java
 * @brief   Implement functions to manage Home Activity of application
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.dsp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.R;
import com.example.recyclerview.cm.cfg.ConfigManager;
import com.example.recyclerview.cm.logger.LoggerManager;
import com.example.recyclerview.handlemanager.wfc.WifiConnection;


/**
 * Class HomeActivity handles main activities
 */
public class HomeActivity extends AppCompatActivity {
  /**
   * Tag for logging
   */
  private static final String TAG = "HOME_ACTIVITY";
  public static final int TABLET_LIST_ACTIVITY_REQUEST_CODE = 1;

  /**
   * WifiConnection instance
   */
  private WifiConnection wifiConn;

  /**
   * Home Activity UI manager instance
   */
  private HomeActivityUIMgr uiComponents;

  /**
   * ConfigManager instance;
   */
  private ConfigManager cfgMgr;
  private LoggerManager loggerManager;
  private ActionBar actionBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.i(TAG, "On Create ... ");
    setContentView(R.layout.activity_main);
    checkPermission();
    loggerManager = LoggerManager.getInstance(this);
    actionBar = getSupportActionBar();

    createObjects();
//    setupCommonFunctions();
//      initUIComponents();
  }


  @Override
  protected void onRestart() {
    super.onRestart();
    Log.i(TAG, "On Restart ... ");
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.i(TAG, "On Start ... ");
  }

  /**
   * Setup common functions
   */
  private void setupCommonFunctions() {
    // TO-DO: setup Logger module
    cfgMgr.setupConfigData();
  }

  /**
   * Get related objects instances
   */
  private void createObjects() {
    Log.d(TAG, "createObjects");
    wifiConn = WifiConnection.getInstance(this);
    uiComponents = HomeActivityUIMgr.getInstance(this);
    cfgMgr = ConfigManager.getInstance(this);
  }

  /**
   * Check request if permission is granted
   */
  public void checkPermission () {
    int checkSelfPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//    Check permission to access external storage
    if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
    }
  }

  /**
   * Handle request permission
   * @param requestCode The request code passed in {@link #requestPermissions(String[], int)}.
   * @param permissions The requested permissions. Never null.
   * @param grantResults The grant results for the corresponding permissions
   */
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case 1000:
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show();
        }
    }
  }
}
