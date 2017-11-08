/*
 * Created by Marina .21/06/17
 *All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.application;

import android.app.Application;
import android.content.Context;

import ua.com.marinanikitenko.purchasesdemo.notification.ChangesObservable;


public class DemoApp extends Application {

    private static DemoApp instance;
    private ChangesObservable mChangesObservable;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mChangesObservable = new ChangesObservable();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public ChangesObservable getChangesObservable(){
        return mChangesObservable;
    }
}
