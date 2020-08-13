/**
 * @file RequestInitSetting.java
 * @brief
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
package com.example.recyclerview.api.request;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.example.recyclerview.api.data.HeaderData;
import com.example.recyclerview.cm.logger.LoggerManager;
import com.example.recyclerview.cm.utils.Defines;

import java.util.Date;


public class RequestInitSetting {
  private static final short DATA_LENGTH = 6;
  private Context context ;
  private Date date = new Date();
  HeaderData headerData;
  private static final String TAG = RequestInitSetting.class.getSimpleName();

//  Mode setting
  private char modeSetting;

//  Life check
  private char lifeCheckCircle;

//  Number of transmissions per unit time (every minute) in designated sensor information
//  notification mode
  private char transmissionNumber;

//  Lamp initial value
  private char lampInit;

// Contact relate setting 1 & 2
  private char relatedSettings_1;
  private char relatedSettings_2;

  public RequestInitSetting () {
    headerData = new HeaderData(Defines.MESS_NUM_0xA0,Defines.MESS_NUM_0xA1, DATA_LENGTH);
    Log.v(TAG,"Constructor");
  }

  public RequestInitSetting (final short SEQUENCE_NUMBER) {
    headerData = new HeaderData(Defines.MESS_NUM_0xA0,Defines.MESS_NUM_0xA1,SEQUENCE_NUMBER, DATA_LENGTH);
    Log.v(TAG,"Constructor");
  }

  public void sendRequestInitSetting (RequestInitSetting requestInitSetting) {
    LoggerManager logger = LoggerManager.getInstance(context);
    logger.Verbose(TAG,"sendRequestInitSetting - Start send data" + date);
    Message message = new Message();
    message.what = 0x01;
    message.obj = requestInitSetting;
    message.sendToTarget();
  }

}
