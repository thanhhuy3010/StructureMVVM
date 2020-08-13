//********************************************************************************************
/**
 * @file    WifiConnectionException.java
 * @brief   Implement functions to handle exception of Wifi connection
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.handlemanager.wfc;

import android.util.Log;

/**
 * Class WifiConnectionException
 */
class WifiConnectionException extends Exception {
  /**
   * Tag of logging
   */
  private static final String TAG = "WIFI_EXCEPTION";

  /**
   * Error code
   */
  private int errorCode;

  /**
   * Error message
   */
  private String errorMsg;

  /**
   * Class constructor
   * @param errCode   int error code
   * @param errMsg    String error message
   */
  WifiConnectionException(int errCode, String errMsg) {
    Log.d(TAG, "Constructor: errCode=" + errCode + ", errMsg=" + errMsg);
    this.errorCode = errCode;
    this.errorMsg = errMsg;
  }

  /**
   * Class constructor
   * @param errMsg    String error message
   */
  WifiConnectionException(String errMsg) {
    Log.d(TAG, "Constructor: errMsg=" + errMsg);
    this.errorCode = -1;
    this.errorMsg = errMsg;
  }

  /**
   * Get formatted message of exception
   * @return String of error code and error message of exception
   */
  public String getMessage() {
    String displayMsg;
    displayMsg = String.format("Exception: %s with error code = %d", errorMsg, errorCode);

    if (displayMsg.isEmpty()) {
      displayMsg = "Cannot get error message.";
    }
    Log.e(TAG, "getMessage: " + displayMsg);
    return displayMsg;
  }
}
