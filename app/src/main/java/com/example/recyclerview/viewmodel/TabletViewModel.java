package com.example.recyclerview.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.recyclerview.model.entity.TabletDeviceEntity;
import com.example.recyclerview.model.repository.TabletRepository;

import java.util.List;

/**
 * @file TabletViewModel.java
 * @brief Original IPC control facility definition
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
public class TabletViewModel extends AndroidViewModel {

  private TabletRepository mRepository;
  private LiveData<List<TabletDeviceEntity>> mAllTablet;

  public TabletViewModel( Application application) {
    super(application);
    mRepository = new TabletRepository(application);
    mAllTablet = mRepository.getGetAllTablet();
  }

  public LiveData<List<TabletDeviceEntity>> getAllTablets() {
    return mAllTablet;
  }

  void insert(TabletDeviceEntity tablet) {
    mRepository.insert(tablet);
  }
}
