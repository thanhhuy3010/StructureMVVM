package com.example.recyclerview.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

/**
 * @file SensorInformation.java
 * @brief Original IPC control facility definition
 * <p>Define Sensor information entity
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */

/**
 * Sensor information table
 */
@Entity(tableName = "sensor_information")
public class SensorInformationEntity {

  @PrimaryKey()
  public String Sensor_ID;

  @ColumnInfo(name = "Sensor_Type")
  public String Sensor_Type;

  @ColumnInfo(name = "Tablet_ID")
  public String Tablet_ID;

  @ColumnInfo(name = "Data")
  public int[] Data;

  @ColumnInfo(name = "Sensor_Status")
  public String Sensor_Status;


}

/**
 * Create relationship for tablet - sensor information (one-many)
 */
class TabletwithSensorInfo {
  @Embedded
  public TabletDeviceEntity tabletDeviceEntity;
  @Relation(
      parentColumn = "Tablet_ID",
      entityColumn = "Tablet_ID"
  )
  public List<TabletDeviceEntity> sensors;
}

