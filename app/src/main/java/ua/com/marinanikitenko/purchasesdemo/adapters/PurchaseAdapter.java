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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ua.com.marinanikitenko.purchasesdemo.activities.PurchaseActivity;
import ua.com.marinanikitenko.purchasesdemo.model.PurchasedProduct;
import ua.com.marinanikitenko.purchasesdemo.model.User;



 /*
  * Show all digital products, available for purchase through Google Play
  */

public class PurchaseAdapter   extends RecyclerView.Adapter<PurchaseAdapter.ViewHolderSettings> {
    private Context context;
    private List<PurchasedProduct> mData;
    private PurchaseActivity purchaseAcrivity;
    private String status;


    public PurchaseAdapter(Context applicationContext, List<PurchasedProduct> purchases) {
        mData = purchases;
        purchaseAcrivity = new PurchaseActivity();

    }

    @Override
    public PurchaseAdapter.ViewHolderSettings onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_card_view, parent, false);
        PurchaseAdapter.ViewHolderSettings holder = new PurchaseAdapter.ViewHolderSettings(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(PurchaseAdapter.ViewHolderSettings holder, int position) {
        PurchasedProduct purchasedProduct = mData.get(position);
        holder.purchasedProduct = purchasedProduct;

        switch (purchasedProduct.getStatus()) {
            case AVAILABLE:
                status = context.getString(R.string.buy);
                break;
            case PURCHASED:
                status = context.getString(R.string.buyed);
                holder.clickView.setEnabled(false);
                break;
        }

        holder.title.setText(purchasedProduct.getName());
        holder.status.setText(status);
        holder.ico.setImageResource(Integer.parseInt(purchasedProduct.getIco()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolderSettings extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView status;
        public ImageView ico;
        public PurchasedProduct purchasedProduct;
        public LinearLayout clickView;
        public User user = User.getInstance();

        public ViewHolderSettings(View itemView) {
            super(itemView);

            ico = (ImageView) itemView.findViewById(R.id.image_recycler_item);
            title = (TextView) itemView.findViewById(R.id.tv_name);
            status = (TextView) itemView.findViewById(R.id.tv_status);
            clickView = (LinearLayout) itemView.findViewById(R.id.category_card_view);
            clickView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.category_card_view:
                    break;
            }
        }
    }
}