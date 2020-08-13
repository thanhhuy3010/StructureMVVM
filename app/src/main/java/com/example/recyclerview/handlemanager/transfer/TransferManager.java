package com.example.recyclerview.handlemanager.transfer;

import android.content.Intent;
import android.os.AsyncTask;

import com.hieng.healthcare.SSSUSimulator.cm.logger.LoggerManager;
import com.hieng.healthcare.SSSUSimulator.model.TabletDatabase;
import com.hieng.healthcare.SSSUSimulator.model.entity.TabletDeviceEntity;

/**
 * @file TransferManager.java
 * @brief Original IPC control facility definition
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
public class TransferManager {
  private LoggerManager loggerManager;
  private TabletDeviceEntity sensorData ;

  public void SensorInformationNotification () {
    loggerManager.Debug("TAG","Message");

  }
}
