package com.zerohour.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.model.InviteHistoryModel;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 5/8/2017.
 */

public class InviteHistoryAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<InviteHistoryModel> inviteHistoryModels;
    private Typeface mMaterialTypeface;


    public InviteHistoryAdapter(DashBoardActivity mParent, ArrayList<InviteHistoryModel> inviteHistoryModels) {
        mLayoutInflater = (LayoutInflater) mParent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMaterialTypeface = Utility.getMaterialIconsRegular(mParent);
        this.inviteHistoryModels = inviteHistoryModels;
    }

    @Override
    public int getCount() {
        return inviteHistoryModels.size();
    }

    @Override
    public InviteHistoryModel getItem(int position) {
        return inviteHistoryModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InviteHistoryItemHolder mInviteHistoryItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_invite_item,
                    null);
            mInviteHistoryItemHolder = new InviteHistoryItemHolder();
            mInviteHistoryItemHolder.tv_month = (TextView) convertView.findViewById(R.id.tv_month);
            mInviteHistoryItemHolder.tv_day = (TextView) convertView.findViewById(R.id.tv_day);
            mInviteHistoryItemHolder.tv_purpose = (TextView) convertView.findViewById(R.id.tv_purpose);
            mInviteHistoryItemHolder.tv_contacts = (TextView) convertView.findViewById(R.id.tv_contacts);
            convertView.setTag(mInviteHistoryItemHolder);
        } else {
            mInviteHistoryItemHolder = (InviteHistoryItemHolder) convertView.getTag();
        }

        InviteHistoryModel inviteHistoryModel = inviteHistoryModels.get(position);

        mInviteHistoryItemHolder.tv_month.setText("" + inviteHistoryModel.getMonth());
        mInviteHistoryItemHolder.tv_day.setText("" + inviteHistoryModel.getDay());
        mInviteHistoryItemHolder.tv_purpose.setText("" + inviteHistoryModel.getPurpose());

        return convertView;
    }

    private class InviteHistoryItemHolder {
        private TextView tv_month;
        private TextView tv_day;
        private TextView tv_purpose;
        private TextView tv_contacts;
    }
}
