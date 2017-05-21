package com.zerohour.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zerohour.DashBoardActivity;
import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.fragments.NoticeBoardDetailFragment;
import com.zerohour.model.NoticeBoardItem;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 5/8/2017.
 */

public class NoticeBoardItemAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<NoticeBoardItem> noticeBoardItems;
    private Typeface mMaterialTypeface;
    private DashBoardActivity mParent;


    public NoticeBoardItemAdapter(DashBoardActivity mParent, ArrayList<NoticeBoardItem> noticeBoardItems) {
        mLayoutInflater = (LayoutInflater) mParent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMaterialTypeface = Utility.getMaterialIconsRegular(mParent);
        this.noticeBoardItems = noticeBoardItems;
        this.mParent = mParent;
    }

    @Override
    public int getCount() {
        return noticeBoardItems.size();
    }

    @Override
    public NoticeBoardItem getItem(int position) {
        return noticeBoardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        NoticeItemHolder mNoticeItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_notice_item,
                    null);
            mNoticeItemHolder = new NoticeItemHolder();
            mNoticeItemHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            mNoticeItemHolder.tv_message = (TextView) convertView.findViewById(R.id.tv_message);
            convertView.setTag(mNoticeItemHolder);
        } else {
            mNoticeItemHolder = (NoticeItemHolder) convertView.getTag();
        }

        NoticeBoardItem noticeBoardItem = noticeBoardItems.get(position);

        mNoticeItemHolder.tv_title.setText("" + noticeBoardItem.getTitle());
        mNoticeItemHolder.tv_message.setText("" + noticeBoardItem.getMessage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.INVITE_HISTORY, noticeBoardItems.get(position));
                Utility.navigateDashBoardFragment(new NoticeBoardDetailFragment(),
                        NoticeBoardDetailFragment.TAG, bundle, mParent);
            }
        });

        return convertView;
    }

    private class NoticeItemHolder {
        private TextView tv_title;
        private TextView tv_message;
    }
}
