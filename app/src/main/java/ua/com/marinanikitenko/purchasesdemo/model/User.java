/*
 * Created by Marina .21/06/17
 * All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ua.com.marinanikitenko.purchasesdemo.application.DemoApp;
import ua.com.marinanikitenko.purchasesdemo.settings.UserPreference;


/**
 * Created by Marina on 26.06.2017.
 */

public class User {

    private static User instance = null;
    public String name = " ";
    public String email= " ";
    public boolean isAuthorized = false;
    public String icoUrl = "ico";
    public String login = " ";
    public String aboutUser = " ";
    public String town = " ";
    public boolean isRefuseAds = false;
    public List<PurchasedProduct> purchasProducts = new ArrayList<>();


    private User(){
    }

    public static User getInstance(){
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setPurchasProducts(PurchasedProduct product){
        this.purchasProducts.add(product);
    }

    public List<PurchasedProduct> getPurchasProducts() {
        return purchasProducts;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public boolean isRefuseAds() {
        return isRefuseAds;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public void setRefuseAds(boolean refuseAds) {
        isRefuseAds = refuseAds;
    }

    public void setIcoUrl(String icoUrl){
        this.icoUrl = icoUrl;
    }

    public String getIcoUrl(){
        return icoUrl;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setAboutUser(String aboutUser){
        this.aboutUser = aboutUser;
    }

    public String getAboutUser(){
        return aboutUser;
    }

    public void setTown(String town){
        this.town = town;
    }

    public String getTown(){
        return town;
    }

    /**
     * Save user's information in inner memory.
     * @param context
     */

    public void savePreference(Context context){
        UserPreference userPreference = new UserPreference(context);
        userPreference.addEmail(email);
        userPreference.addIsAuthorized(true);
    }

    /**
     * Clear user's information from inner memory.
     *
     */

    public void clear(){
        name = "";
        email= "";
        isAuthorized = false;
        icoUrl = "";
        login = "";
        aboutUser = " ";
        town = "";
        //isRefuseAds = false;
        isAuthorized = false;
        UserPreference userPreference = new UserPreference(DemoApp.getContext());
        userPreference.clear();
    }

    /*
     *Get user from local memory
     */
    public void getUserFromShared(){
        UserPreference userPreference = new UserPreference(DemoApp.getContext());
        name = userPreference.getName();
        email = userPreference.getEmail();
        icoUrl = userPreference.getImage();
        town = userPreference.getTown();
        login = userPreference.getLogin();
        isAuthorized = userPreference.isAuthorized();
    }

}
