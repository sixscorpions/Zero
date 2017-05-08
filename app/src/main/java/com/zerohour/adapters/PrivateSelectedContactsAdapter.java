package com.zerohour.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.fragments.PartyInviteFragment;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 5/8/2017.
 */

public class PrivateSelectedContactsAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mContacts;
    private Typeface mMaterialTypeface;


    public PrivateSelectedContactsAdapter(MainActivity mParent, ArrayList<String> mContacts) {
        mLayoutInflater = (LayoutInflater) mParent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMaterialTypeface = Utility.getMaterialIconsRegular(mParent);
        this.mContacts = mContacts;
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactItemHolder mContactItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_private_dialog_item,
                    null);
            mContactItemHolder = new ContactItemHolder();
            mContactItemHolder.tv_contact = (TextView) convertView.findViewById(R.id.tv_contact);
            mContactItemHolder.tv_contact_remove = (TextView) convertView.findViewById(R.id.tv_contact_remove);
            mContactItemHolder.tv_contact_remove.setTypeface(mMaterialTypeface);
            convertView.setTag(mContactItemHolder);
        } else {
            mContactItemHolder = (ContactItemHolder) convertView.getTag();
        }

        String item = (String) getItem(position);
        mContactItemHolder.tv_contact.setText("" + item);
        mContactItemHolder.tv_contact_remove.setId(position);
        mContactItemHolder.tv_contact_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getId();
                mContacts.remove(position);
                PartyInviteFragment.getInstance().updatedData(mContacts);
                notifyDataSetChanged();
                if (mContacts != null && mContacts.size() <= 0) {
                    PartyInviteFragment.mDialog.dismiss();
                }
            }
        });

        return convertView;
    }

    private class ContactItemHolder {
        private TextView tv_contact;
        private TextView tv_contact_remove;
    }
}
