package com.example.recyclerview.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.recyclerview.model.dao.DataDao;
import com.example.recyclerview.model.entity.TabletDeviceEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @file TabletDatabase.java
 * @brief Original IPC control facility definition
 * <p> TabletDB is a database layer on top of an SQLite database
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
@Database(entities = {TabletDeviceEntity.class}, version = 3, exportSchema = false)
public abstract class TabletDatabase extends RoomDatabase {
  private static final String TAG = TabletDatabase.class.getSimpleName();
  /**
   * Create thread for maximum 8 devices
   */
  private static volatile TabletDatabase INSTANCE;
  private static final int NUMBER_OF_THREADS = 8;
  public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  public abstract DataDao dataDao();

  /**
   * Get database from SQLite Database.
   * @param context
   * @return INSTANCE - data of tablet table from SQLite DB.
   */
  public static TabletDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      Log.v(TAG,"getDatabase() - Instance is null, trying synchronized...");
      synchronized (TabletDatabase.class) {
        if (INSTANCE == null) {
          Log.v(TAG,"getDatabase() - trying assign value for INSTANCE with Data " +
              "from tablet Database");
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              TabletDatabase.class, "tablet_database").addCallback(sRoomDatCallback)
              .build();
        }
      }
    }
    Log.v(TAG,"getDatabase() - get instance successful, INSTANCE = " + INSTANCE);
    return INSTANCE;
  }

  private static Callback sRoomDatCallback = new Callback() {
    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
      Log.v(TAG,"sRoomDatCallback callback - open DB success");

      /**
       * If you want to keep data through app restarts,
       * comment out the following block
       */
      databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
          // Populate the database in the background.
          // If you want to start with more tablet, just add them.
          DataDao dao = INSTANCE.dataDao();
          Log.v(TAG, " Add item with more thread ");
          TabletDeviceEntity tablet = new TabletDeviceEntity("test",
              "123.123.123.123",1233,true,1);
          dao.insertTablet(tablet);
          tablet = new TabletDeviceEntity("test123",
              "123.123.123.123",12323,true,2);
          dao.insertTablet(tablet);
          Log.v(TAG, " Add item with more thread - success");
        }
      });
    }
  };
}


