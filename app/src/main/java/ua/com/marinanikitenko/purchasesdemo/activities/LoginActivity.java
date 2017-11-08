/*
 * Created by Marina .21/06/17
 * Copyright (c) 2017 Teamgear. All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import ua.com.teamgear.beedemo.R;
import ua.com.teamgear.beedemo.application.BeeKeeperApp;
import ua.com.teamgear.beedemo.model.Global;
import ua.com.teamgear.beedemo.model.User;
import ua.com.teamgear.beedemo.server.GoogleConnection;
import ua.com.teamgear.beedemo.utils.BuildVersionCheck;

/*
 * Show to user opportunity login in Google account
 */


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private User user;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private GoogleConnection googleConnection;
    private BuildVersionCheck buildVersionCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        initViews();
    }

/*
 * Init all views
 */
    private void initViews() {

        user = User.getInstance();

        signInButton = (SignInButton) findViewById(R.id.google_sign_in);
        signInButton.setOnClickListener(this);

        TextView textView = (TextView) signInButton.getChildAt(0);
        textView.setText(getString(R.string.signin_google));

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in:
                login();
                break;
        }
    }

    private void login() {
        //check ig google services available
        buildVersionCheck = new BuildVersionCheck(this);
        buildVersionCheck.checkVersion();

        googleConnection = new GoogleConnection(this);
        if (Global.isGooglePlayServicesAvailable(this)) {
            if (Global.isNetworkAvailable(this)) {
                googleConnection.signIn();
            } else {
                Toast.makeText(this, getResources().getString(R.string.no_inet),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_google_play),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void close() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.stopAutoManage(this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.stopAutoManage(this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GoogleConnection.RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    // Signed in successfully, show authenticated UI.
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();

            if (acct.getPhotoUrl() != null) {
                user.setIcoUrl(acct.getPhotoUrl().toString());
            }
            user.setName(acct.getDisplayName());
            user.setEmail(acct.getEmail());
            user.setAuthorized(true);
            user.savePreference(BeeKeeperApp.getContext());
            Toast.makeText(this, getResources().getString(R.string.profile_login_success),
                    Toast.LENGTH_SHORT).show();


            BeeKeeperApp beeKeeperApp = (BeeKeeperApp) getApplication();
            beeKeeperApp.getChangesObservable().notifyUserChanges(user);


            finish();

        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}