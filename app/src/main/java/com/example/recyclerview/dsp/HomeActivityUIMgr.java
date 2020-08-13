//********************************************************************************************
/**
 * @file    HomeScreenUIMgr.java
 * @brief   Implement functions to manage UI components on Home Screen
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.dsp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hieng.healthcare.SSSUSimulator.R;
import com.hieng.healthcare.SSSUSimulator.cm.cfg.ConfigManager;

/**
 * Class to manage UI components on Home Screen
 */
public class HomeActivityUIMgr {
  /**
   * Tag of logging
   */
  private static final String TAG = "HOME_ACTIVITY_UI_MGR";

  private static HomeActivityUIMgr uiMgr;
  /**
   * Object to global information about an application environment
   */
  private static Context context;

  /**
   * TextView displays name of currently connected Wifi
   */
  private TextView tvWifiName;

  /**
   * TextView displays IP address of this device
   */
  private TextView tvIpAddress;

  /**
   * TextView displays string acquired from native
   */
  private TextView tvTestJNI;

  private RecyclerView recyclerView;

  /**
   * Get instance of HomeActivityUIMgr; create a new one if it's null
   * @param context   Application Context instance
   * @return  instance of HomeActivityUIMgr
   */
  public static HomeActivityUIMgr getInstance(Context context) {
    if (uiMgr == null) {
      uiMgr = new HomeActivityUIMgr(context);
    }
    return uiMgr;
  }

  /**
   * Constructor
   * @param context Context object of application
   */
  private HomeActivityUIMgr(Context context) {
    Log.d(TAG, "Constructor");
    this.context = context;
  }

  /**
   * Assign resource ID of each component to local variable
   */
  void initComponents() {
    Log.d(TAG, "initComponents");
//    tvWifiName = ((Activity) context).findViewById(R.id.tvWifiName);
//    tvIpAddress = ((Activity) context).findViewById(R.id.tvIPaddress);
//    tvTestJNI = ((Activity) context).findViewById(R.id.tvTestJNI);
    String jni = HomeActivityJNI.stringFromJNI();
    tvTestJNI.setText(jni);
//    recyclerView = ((Activity) context).findViewById(R.id.recyclerview);

  }

  /**
   * Update Wifi information on Home Screen
   * @param ssid        Wifi SSID
   * @param ipAddress   IP address of device
   */
  public static void updateWifiInfo(final String ssid, final String ipAddress) {
    ((Activity) context).runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.d(TAG, "updateWifiInfo() with ssid=" + ssid + ", ipAddress=" + ipAddress);
        Resources res = context.getResources();

//        TextView tvSsid = ((Activity) context).findViewById(R.id.tvWifiName);
//        String ssidText = String.format(res.getString(R.string.wifi_SSID), ssid);
//        tvSsid.setText(ssidText);
//
//        TextView tvIp = ((Activity) context).findViewById(R.id.tvIPaddress);
//        String ipText = String.format(res.getString(R.string.ip_address), ipAddress);
//        tvIp.setText(ipText);
//
//        TextView tvTestJNI = ((Activity) context).findViewById(R.id.tvTestJNI);
        int data = ConfigManager.getLifeCheckPeriod();
        String s = Integer.toString(data);
        Log.v("TAG", "Check connection" + s);
//        tvTestJNI.setText("Get config variable: lifeCheckPeriod=" + s);
      }
    });
  }
}
