/*
 * Created by Marina .21/06/17
 * All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ua.com.teamgear.beedemo.R;

/**
 * Show all user information
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolderProfile> {

    private Context context;
    private List<String> mUser;
    private List<String> mData;

    public ProfileAdapter(Context applicationContext, List<String> userInfo, List<String> item) {
        mUser = userInfo;
        mData = item;
    }


    @Override
    public ProfileAdapter.MyViewHolderProfile onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_card_view, parent, false);
        ProfileAdapter.MyViewHolderProfile holder = new ProfileAdapter.MyViewHolderProfile(v);
        context = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolderProfile holder, int position) {
        holder.userData.setText(mUser.get(position));
        holder.field.setText(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolderProfile extends RecyclerView.ViewHolder {

       public TextView userData;
        public TextView field;

        public MyViewHolderProfile(View itemView) {
            super(itemView);

            userData = (TextView) itemView.findViewById(R.id.tv_user);
            field = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
