/*
 * Created by Marina 21/06/17
 * All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;

import ua.com.marinanikitenko.purchasesdemo.activities.LoginActivity;


/**
 *
 * Helper for check android build version
 */

public class BuildVersionCheck {
    private LoginActivity activity;
    private String TAG = BuildVersionCheck.class.getSimpleName();

    public BuildVersionCheck(LoginActivity loginActivity){
        this.activity = loginActivity;
    }

    public void checkVersion() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            showDialog();
        }
    }


    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.profile_login))
                .setIcon(R.drawable.ic_launcher)
                .setMessage(activity.getString(R.string.dialog_text))
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                activity.close();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
