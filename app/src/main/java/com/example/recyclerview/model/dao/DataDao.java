package com.example.recyclerview.model.dao;
/**
 * @file DataDao.java
 * @brief Original IPC control facility definition
 * <p> validates SQL at compile-time and associates it with a method. Room create a clean API for code.
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.recyclerview.model.entity.TabletDeviceEntity;

import java.util.List;


@Dao
public interface DataDao {
  /**
   * insert tablet to add tablet item into db
   * @param tabletDevices - tablet device object
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertTablet(TabletDeviceEntity... tabletDevices);

  /**
   * Queries Live list from DB
   * @return
   */
  @Query( "SELECT * FROM tablet_entity")
  LiveData<List<TabletDeviceEntity>> loadAllTablets();

  /**
   * Delete tablet
   * @param tabletDevices
   */
  @Delete
  void deleteTablet(TabletDeviceEntity... tabletDevices);

}
