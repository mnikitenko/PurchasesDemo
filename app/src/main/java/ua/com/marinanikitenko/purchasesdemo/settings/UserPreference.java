/*
 * Created by Marina 21/06/17
 *All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.settings;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Helper for save user in local storage
 */

public class UserPreference {
    private final String NAME_FILE_USER = "user";
    private final String FIRST_NAME = "firstname";
    private final String SECOND_NAME = "secondname";
    private final String PHONE = "phone";
    private final String TOWN = "town";
    private final String EMAIL = "email";
    private final String IMAGE = "image";
    private final String GOOGLE_NAME = "googlename";
    private final String IS_AUTHORIZED = "isAuthorized";
    private final String LOGIN = "login";

    private SharedPreferences sPref;

    public UserPreference(Context context){
        sPref = context.getSharedPreferences(NAME_FILE_USER, Context.MODE_PRIVATE);
    }


    // Add a name of user to the store.
    public void addFirstName(String firstNameUser){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(FIRST_NAME, firstNameUser);
        editor.apply();
    }

    // Add a name of user to the store.
    public void addSecondName(String secondNameUser){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SECOND_NAME, secondNameUser);
        editor.apply();
    }

    // Add a phone of user to the store.
    public void addPhone(String phone){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(PHONE, phone);
        editor.apply();
    }
    // Add a town of user to the store.
    public void addTown(String town){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(TOWN, town);
        editor.apply();
    }
    // Add a email of user to the store.
    public void addEmail(String email){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }
    // Add a status of user to the store.
    public void addIsAuthorized(Boolean isAuthorized){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putBoolean(IS_AUTHORIZED, isAuthorized);
        editor.apply();
    }


    public String getFirstName(){
        return sPref.getString(FIRST_NAME, "");
    }

    public String getSecondName(){
        return sPref.getString(SECOND_NAME, "");
    }

    public String getPhone(){
        return sPref.getString(PHONE, "");
    }

    public String getTown(){
        return sPref.getString(TOWN, "");
    }

    public String getEmail(){
        return sPref.getString(EMAIL, "");
    }

    public String getName(){
        return sPref.getString(GOOGLE_NAME, "");
    }

    public Boolean isAuthorized(){
        return sPref.getBoolean(IS_AUTHORIZED, false);
    }

    public String getImage(){
        return sPref.getString(IMAGE, "");
    }

    public String getLogin(){
        return sPref.getString(LOGIN, "");
    }

    //clear user settings
    public void clear(){
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear();
        editor.apply();
    }
}
