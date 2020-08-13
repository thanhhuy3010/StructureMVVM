package com.example.recyclerview.model.entity;

/**
 * @file TabletDevice.java
 * @brief Original IPC control facility definition
 * <p> Define Tablet entity for DB
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Declare
 */
@Entity(tableName = "tablet_entity")
public class TabletDeviceEntity {
  @NonNull
  @PrimaryKey
  public String Tablet_ID;

  @ColumnInfo(name = "IP_Address")
  public String IP_Address;

  @Nullable
  @ColumnInfo(name = "Port")
  public int Port;

  @Nullable
  @ColumnInfo(name = "Connection_Status")
  public boolean Connection_Status;

  @Nullable
  @ColumnInfo(name = "Mode")
  public int Mode;

  public TabletDeviceEntity( String Tablet_ID, String IP_Address, int Port, boolean Connection_Status, int Mode) {
    this.Tablet_ID = Tablet_ID;
    this.IP_Address = IP_Address;
    this.Port = Port;
    this.Connection_Status = Connection_Status;
    this.Mode = Mode;
  }

  /**
   * Implement get table tablet
   * @return
   */
  public String getTabletID(){return this.Tablet_ID;}

}

