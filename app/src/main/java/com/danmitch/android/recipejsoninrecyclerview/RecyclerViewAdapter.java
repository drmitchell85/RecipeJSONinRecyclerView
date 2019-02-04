package com.danmitch.android.recipejsoninrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    // vars
    private Context mContext;
    private ArrayList<User> mUsers = new ArrayList<>();
    private String mFirstName, mLastName, mPhoneNumber, mCountry;

    public RecyclerViewAdapter(Context context, ArrayList<User> users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: started");

        User currentUser = mUsers.get(position);
        mFirstName = currentUser.getFirstName();
        mLastName = currentUser.getLastName();
        mPhoneNumber = currentUser.getPhoneNumber();
        mCountry = currentUser.getCountry();

        holder.txtViewFirstName.setText(mFirstName);
        holder.txtViewLastName.setText(mLastName);
        holder.txtViewPhoneNumber.setText(mPhoneNumber);
        holder.txtViewCountry.setText(mCountry);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewFirstName, txtViewLastName, txtViewPhoneNumber, txtViewCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewFirstName = itemView.findViewById(R.id.txtView_first_name);
            txtViewLastName = itemView.findViewById(R.id.txtView_last_name);
            txtViewPhoneNumber = itemView.findViewById(R.id.txtView_phone_number);
            txtViewCountry = itemView.findViewById(R.id.txtView_country);
        }
    }

}
