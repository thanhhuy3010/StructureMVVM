package com.example.recyclerview.model.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;


import com.example.recyclerview.model.TabletDatabase;
import com.example.recyclerview.model.dao.DataDao;
import com.example.recyclerview.model.entity.TabletDeviceEntity;

import java.util.List;

/**
 * @file TabletRepository.java
 * @brief Original IPC control facility definition
 * <p> The TabletRepository implements the logic for deciding
 * use results cached in a local database.</p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
public class TabletRepository {

  private static final String TAG = TabletRepository.class.getSimpleName();
  private DataDao dataDao;
  private LiveData<List<TabletDeviceEntity>> getAllTablet;

  /**
   * Declare constructor with below param
   * @param application
   */
  public TabletRepository (Application application) {
    TabletDatabase db = TabletDatabase.getDatabase(application);
    Log.v(TAG,"TabletRepository() - Get database from TabletDatabase Instance");
    dataDao = db.dataDao();
    getAllTablet = dataDao.loadAllTablets();
  }
  public LiveData<List<TabletDeviceEntity>> getGetAllTablet() {
    return getAllTablet;
  }
  // You must call this on a non-UI thread or your app will throw an exception. Room ensures
  // that you're not doing any long running operations on the main thread, blocking the UI.
  public void insert(final TabletDeviceEntity tablet) {
    TabletDatabase.databaseWriteExecutor.execute(new Runnable() {
      @Override
      public void run() {
        dataDao.insertTablet(tablet);
      }
    });
  }

}
