//********************************************************************************************
/**
 * @file        HomeScreenJNI.java
 * @brief       Define JNI functions to access native library
 *
 *  Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
//********************************************************************************************

package com.example.recyclerview.dsp;

/**
 * Wrapper for native library
 */
class HomeActivityJNI {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * Get string from the 'native-lib' native library to display on screen
     */
    static native String stringFromJNI();
}
