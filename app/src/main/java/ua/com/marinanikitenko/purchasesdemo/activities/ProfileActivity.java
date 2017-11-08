/*
 * Created by Marina .21/06/17
 * Copyright (c) 2017 Teamgear. All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ua.com.teamgear.beedemo.R;
import ua.com.teamgear.beedemo.adapters.ProfileAdapter;
import ua.com.teamgear.beedemo.application.BeeKeeperApp;
import ua.com.teamgear.beedemo.model.User;
import ua.com.teamgear.beedemo.server.GoogleConnection;

/*
 * Show users information
 */
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private List<String> userInfo;
    private List<String> item;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProfileAdapter mAdapter;
    private CircleImageView avatar;
    private User user;
    private TextView email;
    private AppBarLayout mAppBarLayout;
    private RelativeLayout userView;
    private Button logout;
    private GoogleConnection googleConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        googleConnection = new GoogleConnection(this);
        user = User.getInstance();
        initToolbar();
        getUser();
        initViews();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /*
     *Init all views
     */
    private void initViews() {
        //change title color when collapset to transparent
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));


        userView = (RelativeLayout)findViewById(R.id.view_user_image);
        mAppBarLayout = (AppBarLayout)findViewById(R.id.app_bar_profile) ;
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == -collapsingToolbarLayout.getHeight() + toolbar.getHeight()) {
                    //toolbar is collapsed here
                    userView.setVisibility(View.INVISIBLE);
                }
                else{
                    userView.setVisibility(View.VISIBLE);
                }
            }
        });

        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);
        email = (TextView)findViewById(R.id.user_email);
        email.setText(user.getEmail());
        avatar = (CircleImageView) findViewById(R.id.user_photo);

        if(!user.getIcoUrl().isEmpty() && !user.getIcoUrl().equals(null)) {
            Picasso picasso = Picasso.with(this);
            picasso.load(user.getIcoUrl())
                    .resize(200, 200)
                    .centerInside()
                    .error(R.drawable.user_avatar)
                    .into(avatar);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.profile_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ProfileAdapter(getApplicationContext(), userInfo,item);
        mRecyclerView.setAdapter(mAdapter);

    }

    /*
     *Get user data
     */
    private void getUser() {
        userInfo = new ArrayList<>(Arrays.asList(user.getName(), user.getEmail(), user.getTown(), user.getLogin(), user.getAboutUser()));
        item = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.user_profile)
        ));
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout:
                googleConnection.signOut();
                user.clear();
                Toast.makeText(this, getResources().getString(R.string.profile_log_out),
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }


    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleConnection.RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    // Signed in successfully, show authenticated UI.
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            if(acct.getPhotoUrl() != null ) {
                user.setIcoUrl(acct.getPhotoUrl().toString());
            }
            user.setName(acct.getDisplayName());
            user.setEmail(acct.getEmail());
            user.setAuthorized(true);
            user.savePreference(BeeKeeperApp.getContext());
            Toast.makeText(this, getResources().getString(R.string.profile_login_success),
                    Toast.LENGTH_SHORT).show();

            finish();

        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this, getResources().getString(R.string.profile_login_no_success),
                    Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        googleConnection.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(googleConnection.getGoogleApiClient()!= null) {
            googleConnection.disconnect();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(googleConnection.getGoogleApiClient()!= null) {
            googleConnection.disconnect();
        }
    }
}
