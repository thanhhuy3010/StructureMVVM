//********************************************************************************************
/**
 * @file    WifiConnection.java
 * @brief   Implement functions to manage Wifi connection of application
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.handlemanager.wfc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * WifiConnection class has responsibility to listen Wifi connection state
 * changed, then get SSID and IP address to display on UI.
 */
public class WifiConnection extends BroadcastReceiver {
  /**
   * Tag of logging
   */
  private static final String TAG = "WIFI_MGR";

  /**
   * Instance of WifiConnection
   */
  private static WifiConnection wifiConn;

  /**
   * Object to global information about an application environment
   */
  private Context context;

  /**
   * SSID of the current connected Wifi network
   */
  private String ssid;

  /**
   * Ip address of this device
   */
  private String ipAddress;

  /**
   * Indicate whether wifi is connected or not
   */
  private boolean isWifiConnected;
  
  /**
   * Get instance of WifiConnection; create a new one if it's null
   * @param context   Application Context instance
   * @return  instance of WifiConnection
   */
  public static WifiConnection getInstance(Context context) {
    if (wifiConn == null) {
      wifiConn = new WifiConnection(context);
    }
    return wifiConn;
  }

  /**
   * Constructor
   * @param context Context object of application
   */
  private WifiConnection(Context context) {
    Log.d(TAG, "Constructor");
    this.context = context;
  }

  /**
   * Initialize:
   * - Register listener
   */
  public void initialize() {
    Log.d(TAG, "Initialize");
    registerListener();
  }

  /**
   * Finalize:
   * - Unregister listener
   */
  public void finalize() {
    Log.d(TAG, "Finalize");
    unregisterListener();
  }

  /**
   * Register NETWORK_STATE_CHANGED_ACTION to listen on network state
   */
  public void registerListener() {
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
    context.registerReceiver(this, intentFilter);
  }

  /**
   * Unregister NETWORK_STATE_CHANGED_ACTION
   */
  private void unregisterListener() {
    Log.d(TAG, "Unregister listener");
    try {
      context.unregisterReceiver(this);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(TAG, "onReceive");
    try {
      /* Get updated wifi connection information*/
      getWifiConnectionInfo();
    } catch (WifiConnectionException e) {
      Log.e(TAG, e.getMessage());
    }
    /* Update latest wifi information on screen */
    updateUI();
  }

  /**
   * Get SSID and IP address from WifiInfo then assign to local variables
   *
   * @throws WifiConnectionException if there is no Wifi connected
   */
  private void getWifiConnectionInfo() throws WifiConnectionException {
    Log.d(TAG, "getWifiConnectionInfo");
    WifiInfo mWifiInfo = getWifiInfo();
    getSsidFromWifiInfo(mWifiInfo);
    getIpFromWifiInfo(mWifiInfo);
  }

  /**
   * Get Wifi connection information
   *
   * @return WifiInfo object
   */
  private WifiInfo getWifiInfo() throws WifiConnectionException {
    WifiManager wifiManager = getWifiManager();
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();

    /* WifiInfo will be null in airplane mode */
    if (wifiInfo == null) {
      throw new WifiConnectionException("Network info is not available.");
    }

    SupplicantState state = wifiInfo.getSupplicantState();
    if (wifiManager.isWifiEnabled() && state == SupplicantState.COMPLETED) {
      Log.d(TAG, "Wifi connected.");
      isWifiConnected = true;
    } else {
      Log.d(TAG, "Wifi disconnected.");
      isWifiConnected = false;
    }
    return wifiInfo;
  }

  /**
   * Get Wifi Manager object
   * @return Wifi Manager object
   */
  private WifiManager getWifiManager() {
    Log.d(TAG, "getWifiManager");
    return (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
  }

  /**
   * Get SSID of Wifi network
   * @param wifiInfo WifiInfo object
   */
  private void getSsidFromWifiInfo(WifiInfo wifiInfo) {
    ssid = wifiInfo.getSSID();
    Log.d(TAG, "getSsidFromWifiInfo(): SSID=" + ssid);
  }

  /**
   * Get IP address of this device - CHECK
   * @param wifiInfo WifiInfo object
   */
  @SuppressLint("DefaultLocale")
  private void getIpFromWifiInfo(WifiInfo wifiInfo) {
    int ip = wifiInfo.getIpAddress();
    ipAddress = String.format("%d.%d.%d.%d",
      (ip & 0xff),
      (ip >> 8 & 0xff),
      (ip >> 16 & 0xff),
      (ip >> 24 & 0xff));
    Log.d(TAG, "getIpFromWifiInfo: ipAddress=" + ipAddress);
  }

  /**
   * Call UIUpdater to update TextView objects on UI which according SSID and IP address
   */
  private void updateUI() {
    Log.d(TAG, "updateUI");
//    HomeActivityUIMgr.updateWifiInfo(ssid, ipAddress);
  }

  /**
   * Check if wifi is connected or not.
   * @return true if wifi is connected
   */
  public boolean isWifiConnected() {
    Log.d(TAG, "isWifiConnected=" + isWifiConnected);
    return isWifiConnected;
  }
}
