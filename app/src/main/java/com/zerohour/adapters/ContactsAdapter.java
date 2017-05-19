package com.zerohour.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinnedlistview.SearchablePinnedHeaderListViewAdapter;
import com.pinnedlistview.StringArrayAlphabetIndexer;
import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.fragments.PartyInviteFragment;
import com.zerohour.model.Contact;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by shankar on 5/17/2017.
 */

public class ContactsAdapter extends SearchablePinnedHeaderListViewAdapter<Contact> {

    private ArrayList<Contact> mContacts;
    private ArrayList<Contact> sortedList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] mCodeList;

    public ContactsAdapter(DashBoardActivity parent, final ArrayList<Contact> contacts) {
        mInflater = LayoutInflater.from(parent);
        mContext = parent;
        setData(contacts);
    }

    public void setData(final ArrayList<Contact> contacts) {
        this.mContacts = contacts;


        final String[] generatedContactNames = generateContactNames(contacts);
        setSectionIndexer(new StringArrayAlphabetIndexer(generatedContactNames, true));
    }

    private String[] generateContactNames(final List<Contact> contacts) {
        final ArrayList<String> contactNames = new ArrayList<>();
        if (contacts != null)
            for (final Contact contactEntity : contacts)
                contactNames.add(contactEntity.displayName);
        return contactNames.toArray(new String[contactNames.size()]);
    }

    @Override
    public CharSequence getSectionTitle(int sectionIndex) {
        return ((StringArrayAlphabetIndexer.AlphaBetSection) getSections()[sectionIndex]).getName();
    }


    @Override
    public boolean doFilter(Contact contact, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence))
            return true;
        final String displayName = contact.displayName;
        return !TextUtils.isEmpty(displayName) && displayName.toLowerCase(Locale.getDefault())
                .contains(charSequence.toString().toLowerCase(Locale.getDefault()));
    }

    @Override
    public ArrayList<Contact> getOriginalList() {
        return mContacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        View rootView;
        if (convertView == null) {
            holder = new ViewHolder();
            rootView = mInflater.inflate(R.layout.row_contacts_lv_items, viewGroup, false);
            holder.tvName = (TextView) rootView.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) rootView.findViewById(R.id.tv_number);
            holder.ivPicture = (ImageView) rootView.findViewById(R.id.iv_Picture);
            holder.check = (CheckBox) rootView.findViewById(R.id.check);
            rootView.setTag(holder);
        } else {
            rootView = convertView;
            holder = (ViewHolder) rootView.getTag();
        }


        final Contact contact = getItem(position);

        if (contact.ismContacts_Flow()) {
            holder.check.setChecked(true);

        } else {
            holder.check.setChecked(false);
        }
        String displayName = contact.displayName;
        holder.tvName.setText(displayName);
        //holder.tvName.setTypeface(Utility.getArialTypeface(mContext));

        String number = contact.phoneNumber;
        holder.tvPhone.setText(number);
        // holder.tvPhone.setTypeface(Utils.getArialTypeface(mContext));


        /*holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                if (checkBox.isChecked()) {
                    contact.setCheckBox(true);
                } else {
                    contact.setCheckBox(false);
                }

            }
        });*/

        holder.check.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;

                if (checkBox.isChecked()) {
                    contact.setmContacts_Flow(true);
                    contact.setCheckBox(true);
                    JSONObject jobj = new JSONObject();
                    if (contact.getCheckBox()) {
                        String countryCode;
                        String onlynumber;
                        String num = contact.getPhoneNumber().trim();
                        num = num.replace(" ", "");
                        num = num.replace("-", "");
                        String name = contact.getDisplayName();
                        if (num.contains("+") && num.length() > 10) {
                            countryCode = num.substring(0, num.length() - 10);
                            onlynumber = num.substring(num.length() - 10);
                            try {
                                jobj.put("contact_name", name);
                                jobj.put("contact_number", onlynumber);
                                jobj.put("isd_code", countryCode);
                                PartyInviteFragment.contactsarray.put(jobj);
                                if (PartyInviteFragment.contactsListModel.size() > 0) {
                                    if (!PartyInviteFragment.contactsListModel.contains(onlynumber))
                                        PartyInviteFragment.contactsListModel.add(onlynumber);
                                } else
                                    PartyInviteFragment.contactsListModel.add(onlynumber);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    contact.setmContacts_Flow(false);
                    contact.setCheckBox(false);
                    for (int i = 0; i < PartyInviteFragment.contactsarray.length(); i++) {
                        PartyInviteFragment.contactsarray.remove(i);
                    }
                }
            }
        });
        return rootView;
    }

    private static class ViewHolder {
        private TextView tvName;
        private TextView tvPhone;
        private CheckBox check;
        private ImageView ivPicture;
    }

    public void updateAdapter(ArrayList<Contact> mList) {
        this.mContacts = mList;
        setData(mList);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Contact> FilteredArrList = new ArrayList<>();


                if (sortedList == null) {
                    sortedList = new ArrayList<>(mContacts); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = sortedList.size();
                    results.values = sortedList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < sortedList.size(); i++) {
                        Contact data = sortedList.get(i);
                        if (data.getDisplayName().toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mContacts = (ArrayList<Contact>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
