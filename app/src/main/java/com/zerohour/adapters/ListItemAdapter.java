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
import com.zerohour.model.LeftDrawerItem;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 5/8/2017.
 */

public class ListItemAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<LeftDrawerItem> leftDrawerItems;
    private Typeface mMaterialTypeface;


    public ListItemAdapter(DashBoardActivity mParent, ArrayList<LeftDrawerItem> leftDrawerItems) {
        mLayoutInflater = (LayoutInflater) mParent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMaterialTypeface = Utility.getMaterialIconsRegular(mParent);
        this.leftDrawerItems = leftDrawerItems;
    }

    @Override
    public int getCount() {
        return leftDrawerItems.size();
    }

    @Override
    public LeftDrawerItem getItem(int position) {
        return leftDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListItemHolder mListItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item,
                    null);
            mListItemHolder = new ListItemHolder();
            mListItemHolder.tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);
            mListItemHolder.tv_app_name = (TextView) convertView.findViewById(R.id.tv_app_name);
            convertView.setTag(mListItemHolder);
        } else {
            mListItemHolder = (ListItemHolder) convertView.getTag();
        }

        LeftDrawerItem noticeBoardItem = leftDrawerItems.get(position);

        mListItemHolder.tv_icon.setText("" + noticeBoardItem.getIcon());
        mListItemHolder.tv_icon.setTypeface(mMaterialTypeface);
        mListItemHolder.tv_app_name.setText("" + noticeBoardItem.getName());

        return convertView;
    }

    private class ListItemHolder {
        private TextView tv_icon;
        private TextView tv_app_name;
    }
}
