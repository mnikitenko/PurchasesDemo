/*
 * Created by Marina .21/06/17
 *  All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.model;


public class Log {

    private static final String TAG = "demo";
    private static boolean logEnabled = false; //true;//false;

    public static void i(String tag, String string) {
        if (logEnabled) android.util.Log.i(tag, string);
    }
    public static void e(String tag, String string) {
        if (logEnabled) android.util.Log.e(tag, string);
    }
    public static void e(String tag, String string, Exception e) {
        if (logEnabled) android.util.Log.e(tag, string, e);
    }
    public static void d(String tag, String string) {
        if (logEnabled) android.util.Log.d(tag, string);
    }
    public static void v(String tag, String string) {
        if (logEnabled) android.util.Log.v(tag, string);
    }
    public static void w(String tag, String string) {
        if (logEnabled) android.util.Log.w(tag, string);
    }

    public static void i(String string) {
        if (logEnabled) android.util.Log.i(TAG, string);
    }
    public static void e( String string) {
        if (logEnabled) android.util.Log.e(TAG, string);
    }
    public static void e(String string, Exception e) {
        if (logEnabled) android.util.Log.e(TAG, string, e);
    }
    public static void d(String string) {
        if (logEnabled) android.util.Log.d(TAG, string);
    }
    public static void v( String string) {
        if (logEnabled) android.util.Log.v(TAG, string);
    }
    public static void w(String string) {
        if (logEnabled) android.util.Log.w(TAG, string);
    }
}

