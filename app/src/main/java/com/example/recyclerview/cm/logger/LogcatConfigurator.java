package com.example.recyclerview.cm.logger;

/**
 * @file .java
 * @brief Original IPC control facility definition
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */

public class LogcatConfigurator {
  private final long maxFileSize = 5120;
  private String fileName = "SSSUService.log";
  public LogcatConfigurator(final String fileName) {
    setFileName(fileName);
  }

  public void setFileName ( String fileName) {
    this.fileName = fileName;
  }

  public long getMaxSizeData () {
    return maxFileSize;
  }







}
