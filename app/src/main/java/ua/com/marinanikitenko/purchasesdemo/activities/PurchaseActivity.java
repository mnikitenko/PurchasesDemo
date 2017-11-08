/*
 * Created by Marina .21/06/17
 * Copyright (c) 2017 Teamgear. All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ua.com.teamgear.beedemo.R;
import ua.com.teamgear.beedemo.adapters.PurchaseAdapter;
import ua.com.teamgear.beedemo.application.BeeKeeperApp;
import ua.com.teamgear.beedemo.enums.PurchaseStatus;
import ua.com.teamgear.beedemo.model.PurchasedProduct;
import ua.com.teamgear.beedemo.model.User;
import ua.com.teamgear.beedemo.notification.ObservableItem;

import static ua.com.teamgear.beedemo.model.Log.d;
 /*
  * Show digital products  purchased by the user, their info
  */

public class PurchaseActivity extends AppCompatActivity implements Observer{

    private List<PurchasedProduct> purchases ;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PurchaseAdapter mAdapter;
    private BeeKeeperApp beeKeeperApp;
    private PurchasedProduct purchasedProduct;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        user = User.getInstance();
        getObserver();
        initToolbar();
        setPurchase();
    }

    private void getObserver(){
        //Register observer
        beeKeeperApp = (BeeKeeperApp) getApplication();
        beeKeeperApp.getChangesObservable().addObserver(this);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setPurchase(){
        if(user.getPurchasProducts().size() == 0) {
            PurchasedProduct purchasedProduct = new PurchasedProduct();
            purchasedProduct.setRefuseAdPurchase(
                    getString(R.string.refusal_descr),
                    getString(R.string.product_refusal_of_ad),
                    Integer.toString(R.drawable.ic_donate_money),
                    PurchaseStatus.AVAILABLE);

            if(user.isRefuseAds()){
                purchasedProduct.setStatus(PurchaseStatus.PURCHASED);
            }
            user.setPurchasProducts(purchasedProduct);
        }

        setAdapter();

    }

    private void setAdapter(){
        mRecyclerView = (RecyclerView) findViewById(R.id.settings_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PurchaseAdapter(getApplicationContext(),user.getPurchasProducts());
        mRecyclerView.setAdapter(mAdapter);
    }

    /*
     * Get notification if user buy product
     */
    @Override
    public void update(Observable observable, Object data) {
        ObservableItem observableItem = (ObservableItem)data;
        PurchasedProduct purchasedProduct = observableItem.getPurchasedProduct();

        if(purchasedProduct != null){
            mAdapter.notifyDataSetChanged();

        }
    }



    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Unregister observer
        beeKeeperApp.getChangesObservable().deleteObserver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        d("onPause");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Go to MainActivity.
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
