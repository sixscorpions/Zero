package com.zerohour.fragments;


import android.app.Dialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.adapters.ContactsAdapter;
import com.zerohour.adapters.PrivateSelectedContactsAdapter;
import com.zerohour.interfaces.IUpdateDialogData;
import com.zerohour.interfaces.IUpdateNumberData;
import com.zerohour.model.Contact;
import com.zerohour.model.InviteHistoryModel;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InviteHistoryDetailFragment extends Fragment implements IUpdateDialogData, IUpdateNumberData {

    public static final String TAG = InviteHistoryDetailFragment.class.getSimpleName();
    private DashBoardActivity mParent;
    private View view;
    private InviteHistoryModel inviteHistoryModel;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_purpose)
    TextView tv_purpose;

    @BindView(R.id.et_mobile_number)
    EditText etMobileNumber;
    @BindView(R.id.tv_contacts_image)
    TextView tvContactsImage;
    @BindView(R.id.tv_add_image)
    TextView tv_add_image;

    @BindView(R.id.btn_submit)
    TextView btn_submit;

    @BindView(R.id.ll_first_contact)
    LinearLayout ll_first_contact;
    @BindView(R.id.tv_first_contact)
    TextView tv_first_contact;
    @BindView(R.id.tv_first_contact_remove)
    TextView tv_first_contact_remove;

    @BindView(R.id.ll_second_contact)
    LinearLayout ll_second_contact;
    @BindView(R.id.tv_second_contact)
    TextView tv_second_contact;
    @BindView(R.id.tv_first_second_remove)
    TextView tv_first_second_remove;

    @BindView(R.id.tv_more)
    TextView tv_more;
    private ArrayList<String> inviteSelected;
    public static Dialog mDialog;
    private static IUpdateDialogData iUpdateDialogData;
    private static IUpdateNumberData iUpdateNumberData;

    private Cursor mCursor;
    private Set<Contact> result;
    public static JSONArray contactsarray = new JSONArray();

    public static ArrayList<String> contactsListModel = new ArrayList<>();

    public static IUpdateDialogData getInstance() {
        return iUpdateDialogData;
    }

    public static IUpdateNumberData getInstanceData() {
        return iUpdateNumberData;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iUpdateDialogData = this;
        iUpdateNumberData = this;
        mParent = (DashBoardActivity) getActivity();
        inviteHistoryModel = (InviteHistoryModel) getArguments().getSerializable(Constants.INVITE_HISTORY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_invite_history_detail, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {

        tvContactsImage.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_add_image.setTypeface(Utility.getMaterialIconsRegular(mParent));

        tv_first_contact_remove.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_first_second_remove.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_more.setTypeface(Utility.getMaterialIconsRegular(mParent));

        tv_month.setText("" + inviteHistoryModel.getMonth());
        tv_day.setText("" + inviteHistoryModel.getDay());
        tv_purpose.setText("" + inviteHistoryModel.getPurpose());
        inviteSelected = new ArrayList<>();
    }

    @OnClick(R.id.tv_first_contact_remove)
    void removeFirstContact() {
        inviteSelected.remove(0);
        updateData();
    }

    @OnClick(R.id.tv_first_second_remove)
    void removeSecondContact() {
        inviteSelected.remove(1);
        updateData();
    }

    @OnClick(R.id.tv_more)
    void showMoreDialog() {
        mDialog = new Dialog(mParent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.private_contacts_dialog);
        //dialogShare.getWindow().setGravity(Gravity.BOTTOM);
        mDialog.setCanceledOnTouchOutside(true);
        //dialogShare.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));

        ListView listView = (ListView) mDialog.findViewById(R.id.lv_contacts);
        PrivateSelectedContactsAdapter privateSelectedContactsAdapter = new PrivateSelectedContactsAdapter(mParent, inviteSelected, TAG);
        listView.setAdapter(privateSelectedContactsAdapter);

        mDialog.show();

    }

    @OnClick(R.id.tv_add_image)
    void addNumber() {
        if (isValidFields()) {
            inviteSelected.add(etMobileNumber.getText().toString());
            updateData();
        }
    }

    private boolean isValidFields() {
        boolean isValid = true;
        if (Utility.isValueNullOrEmpty(etMobileNumber.getText().toString())) {
            Utility.showToastMessage(mParent, "Please enter mobile number");
            isValid = false;
        } else if (etMobileNumber.getText().toString().length() < 10) {
            Utility.showToastMessage(mParent, "Please enter valid mobile number");
            isValid = false;
        }
        return isValid;
    }

    private void updateData() {
        if (inviteSelected != null && inviteSelected.size() == 1) {
            etMobileNumber.setText("");
            ll_first_contact.setVisibility(View.VISIBLE);
            tv_first_contact.setText("" + inviteSelected.get(0));
            ll_second_contact.setVisibility(View.GONE);
        } else if (inviteSelected != null && inviteSelected.size() == 2) {
            etMobileNumber.setText("");
            ll_first_contact.setVisibility(View.VISIBLE);
            tv_first_contact.setText("" + inviteSelected.get(0));
            ll_second_contact.setVisibility(View.VISIBLE);
            tv_second_contact.setText("" + inviteSelected.get(1));
            tv_more.setVisibility(View.GONE);
        } else {
            etMobileNumber.setText("");
            if (inviteSelected != null && inviteSelected.size() > 0) {
                ll_first_contact.setVisibility(View.VISIBLE);
                tv_first_contact.setText("" + inviteSelected.get(0));
                ll_second_contact.setVisibility(View.VISIBLE);
                tv_second_contact.setText("" + inviteSelected.get(1));
                tv_more.setVisibility(View.VISIBLE);
            } else {
                ll_first_contact.setVisibility(View.GONE);
                ll_second_contact.setVisibility(View.GONE);
                tv_more.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void updatedData(ArrayList<String> mContacts) {
        inviteSelected = mContacts;
        updateData();
    }

    @OnClick(R.id.tv_contacts_image)
    void pickContact() {
        /*Intent pickContactIntent = new Intent(Intent.ACTION_PICK,
                Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        mParent.startActivityForResult(pickContactIntent, Constants.RESULT_PICK_CONTACT);*/
        final Dialog dialog = new Dialog(mParent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contacts_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true); // can dismiss alert screen when user click back buttonon
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        EditText et_search = (EditText) dialog.findViewById(R.id.et_search);
        TextView tv_et_search_image = (TextView) dialog.findViewById(R.id.tv_et_search_image);
        ListView ll_contacts = (ListView) dialog.findViewById(R.id.ll_contacts);
        TextView tv_pick = (TextView) dialog.findViewById(R.id.tv_pick);

        tv_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactsListModel.size() > 0) {
                    for (String mNum : contactsListModel) {
                        if (!inviteSelected.contains(mNum))
                            inviteSelected.add(mNum);
                    }
                    updateData();
                }
                //Utility.showToastMessage(getActivity(), "SELECTED CONTACTS" + contactsListModel.size());
                contactsListModel.clear();
                dialog.dismiss();
            }
        });
        tv_et_search_image.setTypeface(Utility.getMaterialIconsRegular(mParent));

        mCursor = mParent.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        Set<Contact> contacts = getContacts();
        ArrayList<Contact> newList = new ArrayList<>(new HashSet<>(contacts));

        Collections.sort(newList, new Comparator<Contact>() {
            @Override
            public int compare(Contact lhs, Contact rhs) {
                char lhsFirstLetter = TextUtils.isEmpty(lhs.displayName) ? ' ' : lhs.displayName.charAt(0);
                char rhsFirstLetter = TextUtils.isEmpty(rhs.displayName) ? ' ' : rhs.displayName.charAt(0);
                int firstLetterComparison = Character.toUpperCase(lhsFirstLetter) - Character.toUpperCase(rhsFirstLetter);
                if (firstLetterComparison == 0)
                    return lhs.displayName.compareTo(rhs.displayName);
                return firstLetterComparison;
            }
        });

        final ContactsAdapter mAdapter = new ContactsAdapter(mParent, newList, TAG);
        ll_contacts.setAdapter(mAdapter);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialog.show();
    }

    private Set<Contact> getContacts() {
        if (checkContactsReadPermission()) {
            result = new TreeSet<>();
            if (mCursor != null) {
                Log.e("count", "" + mCursor.getCount());
                while (mCursor.moveToNext()) {
                    String name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String image_uri = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                    if (!name.equals(phoneNumber)) {
                        Contact contact = new Contact(name, phoneNumber, image_uri);
                        contact.setCheckBox(false);
                        result.add(contact);
                    }
                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }
        }
        return result;
    }

    private boolean checkContactsReadPermission() {
        String permission = "android.permission.READ_CONTACTS";
        int res = mParent.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void updatedData(String mContact) {
        if (inviteSelected == null) {
            inviteSelected = new ArrayList<>();
        }
        if (!inviteSelected.contains(mContact))
            inviteSelected.add(mContact);
        else
            Utility.showToastMessage(mParent, "Number Already added");
        updateData();
    }

    @OnClick(R.id.btn_submit)
    void submitData() {
        if (isValidForSms()) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.PURPOSE, inviteHistoryModel.getPurpose());
            bundle.putString(Constants.DATE, "23/06/2017");
            bundle.putString(Constants.TIME, "11.30");
            bundle.putString(Constants.LOCATION, "");
            bundle.putStringArrayList(Constants.CONTACTS, inviteSelected);
            Utility.navigateDashBoardFragment(new PartyInviteResultFragment(),
                    PartyInviteResultFragment.TAG, bundle, mParent);
        }
    }

    private boolean isValidForSms() {
        boolean isValid = true;
        if (inviteSelected != null & inviteSelected.size() <= 0) {
            Utility.showToastMessage(mParent, "Please add at least one number");
            isValid = false;
        }
        return isValid;
    }
}
//