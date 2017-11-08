/*
 * Created by Marina .21/06/17
 *  All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;

import ua.com.marinanikitenko.purchasesdemo.enums.PurchaseStatus;


public class PurchasedProduct extends Observable implements Parcelable {
    public String name;
    public String packageName;
    public boolean isBuyed = false;
    public String ico;
    public PurchaseStatus purchaseStatus = PurchaseStatus.AVAILABLE;


    public PurchasedProduct(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public void setIsBuyed(boolean isBuyed){
        this.isBuyed = isBuyed;
    }

    public void setStatus(PurchaseStatus purchaseStatus){
        this.purchaseStatus = purchaseStatus;
    }

    public PurchaseStatus getStatus(){
        return purchaseStatus;
    }

    public String getName() {
        return name;
    }

    public String getPackageName(){
        return packageName;
    }

    public String getIco(){
        return ico;
    }

    public boolean isBuyed(){
        return isBuyed;
    }

    /*create purchase refuse from ad
    * @param name
    * @param packageName
    * @param ico
    * @param purchaseStatus
    */

    public void setRefuseAdPurchase(String name,String packageName,String ico,PurchaseStatus purchaseStatus ){
        this.name = name;
        this.packageName = packageName;
        this.ico = ico;
        this.purchaseStatus = PurchaseStatus.AVAILABLE;
    }

    // Methods from Parcelable interface.
    @Override
    public int describeContents() {
        return 0;
    }

    // packing object in  Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(packageName);
        dest.writeString(ico);
        dest.writeByte((byte) (isBuyed ? 1 : 0));
    }

    // constructor,read data from Parcel
    protected PurchasedProduct(Parcel in) {
        name = in.readString();
        packageName = in.readString();
        ico = in.readString();
        isBuyed = in.readByte() != 0;
    }

    public static final Creator<PurchasedProduct> CREATOR = new Creator<PurchasedProduct>() {

        // unpacking object from Parcel
        @Override
        public PurchasedProduct createFromParcel(Parcel in) {
            return new PurchasedProduct(in);
        }

        @Override
        public PurchasedProduct[] newArray(int size) {
            return new PurchasedProduct[size];
        }
    };


}

