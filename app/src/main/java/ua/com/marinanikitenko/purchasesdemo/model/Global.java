/*
 * Created by Marina .21/06/17
 * All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.common.GooglePlayServicesUtil;


/**
 * Helper for check network state
 */

public class Global {

    // Method returns true if there is connection to the Internet.
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager	.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //Method returns true if GooglePlayServices Available
    public static boolean isGooglePlayServicesAvailable(Context context) {
        final int statusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (GooglePlayServicesUtil.isUserRecoverableError(statusCode)) {
            // ask user to update google play services.
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(statusCode, (Activity) context, 1);
            dialog.show();
            return false;
        }
        return true;
    }
}

