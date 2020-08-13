package com.example.recyclerview.cm.logger;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @file LoggerManager.java
 * @brief Original IPC control facility definition
 * <p> Implement save log cat to  and handle
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
public class LoggerManager {
  /**
   * Declare Tag for using Log
   */
  private static final String TAG = LoggerManager.class.getSimpleName();
  //Define Logger instance
  private static LoggerManager loggerManager;
  //Define Context instance
  private Context context;

   /**
   * Get instance of LoggerManager; create a new one if it's null
   * @param   context  Application Context instance
   * @return  instance of LoggerManager
   */
  public static LoggerManager getInstance(Context context) {
    Log.d(TAG, "getInstance");
    if (loggerManager == null) {
      loggerManager = new LoggerManager(context);
    }
    return loggerManager;
  }
  /**
   * Constructor
   * @param context Context instance
   */
  private LoggerManager(Context context) {
    Log.d(TAG, "Constructor");
    this.context = context;
  }

  /**
   * Show expected log messages for regular usage, as well as the message levels lower in this list.
   * @param Tag
   * @param Message
   */
  public void Information(String Tag, String Message) {
    Log.i(Tag, Message);
    saveLog(Tag,Message);
  }

  /**
   * Show possible issues that are not yet errors, as well as the message levels lower in this list.
   * @param Tag
   * @param Message
   */
  public void  Warning(String Tag, String Message) {
    Log.w(Tag, Message);
    saveLog(Tag,Message);
  }

  /**
   * Show issues that have caused errors, as well as the message level lower in this list.
   * @param Tag
   * @param Message
   */
  public void  Error(String Tag, String Message) {
    Log.e(Tag, Message);
    saveLog(Tag,Message);
  }

  /**
   * Show debug log messages that are useful during development only, as well as the message levels
   * lower in this list.
   * @param Tag
   * @param Message
   */
  public void Debug(String Tag, String Message) {
    Log.d(Tag, Message);
    saveLog(Tag,Message);
  }

  /**
   * Show all log messages (the default).
   * @param Tag
   * @param Message
   */
  public void  Verbose(String Tag, String Message) {
    Log.v(Tag, Message);
    saveLog(Tag,Message);
  }

  /**
   * Handle save log text to file
   * @param Tag
   * @param Message
   */
  private void saveLog (String Tag, String Message) {
    Log.v(TAG, "Init save log data");
    String message = Tag + " - " + Message + "\n";
    try {
      File file = new File(context.getExternalFilesDir(null).getAbsolutePath() + "logcat.txt");
      Log.v(TAG, "SaveLog() - declare file success: " + file);
      if(!file.exists()) {
        file.createNewFile();
        Log.v(TAG, "SaveLog() - Check file exist: ");
      } else {
        FileOutputStream fos = new FileOutputStream(file,true);
        Log.v(TAG, "SaveLog() - File exist");
        fos.write(message.getBytes());
        Log.v(TAG, "SaveLog() - Write data success");
        fos.close();
      }
      Log.v(TAG, "saveData() - Save success");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      Log.v(TAG, e.getMessage());
    } catch (Exception e ) {
      e.printStackTrace();
      Log.v(TAG, e.getMessage());
    }
  }

}
