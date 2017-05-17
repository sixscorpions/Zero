package com.zerohour.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.adapters.ContactsAdapter;
import com.zerohour.adapters.PrivateSelectedContactsAdapter;
import com.zerohour.interfaces.IUpdateDialogData;
import com.zerohour.interfaces.IUpdateNumberData;
import com.zerohour.model.Contact;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
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
public class PartyInviteFragment extends Fragment implements IUpdateDialogData, IUpdateNumberData {

    private DashBoardActivity mParent;
    private View view;

    @BindView(R.id.et_mobile_number)
    EditText etMobileNumber;
    @BindView(R.id.tv_contacts_image)
    TextView tvContactsImage;
    @BindView(R.id.tv_add_image)
    TextView tv_add_image;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_time)
    TextView tv_time;


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

    @BindView(R.id.tv_current_location_image)
    TextView tv_current_location_image;
    @BindView(R.id.tv_et_reason_image)
    TextView tv_et_reason_image;

    @BindView(R.id.tv_more)
    TextView tv_more;

    @BindView(R.id.et_date)
    EditText et_date;
    @BindView(R.id.et_time)
    EditText et_time;
    @BindView(R.id.et_reason)
    EditText et_reason;

    private ArrayList<String> numbersSelected;
    public static Dialog mDialog;
    private static IUpdateDialogData iUpdateDialogData;
    private static IUpdateNumberData iUpdateNumberData;

    private Cursor mCursor;
    private Set<Contact> result;
    public static JSONArray contactsarray = new JSONArray();

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_party_invite, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        numbersSelected = new ArrayList<>();
        tvContactsImage.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_add_image.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_date.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_time.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_first_contact_remove.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_first_second_remove.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_current_location_image.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_et_reason_image.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_more.setTypeface(Utility.getMaterialIconsRegular(mParent));
    }

    @OnClick(R.id.tv_add_image)
    void addNumber() {
        if (isValidFields()) {
            numbersSelected.add(etMobileNumber.getText().toString());
            updateData();
        }
    }

    private void updateData() {
        if (numbersSelected != null && numbersSelected.size() == 1) {
            etMobileNumber.setText("");
            ll_first_contact.setVisibility(View.VISIBLE);
            tv_first_contact.setText("" + numbersSelected.get(0));
            ll_second_contact.setVisibility(View.GONE);
        } else if (numbersSelected != null && numbersSelected.size() == 2) {
            etMobileNumber.setText("");
            ll_first_contact.setVisibility(View.VISIBLE);
            tv_first_contact.setText("" + numbersSelected.get(0));
            ll_second_contact.setVisibility(View.VISIBLE);
            tv_second_contact.setText("" + numbersSelected.get(1));
            tv_more.setVisibility(View.GONE);
        } else {
            etMobileNumber.setText("");
            if (numbersSelected != null && numbersSelected.size() > 0) {
                ll_first_contact.setVisibility(View.VISIBLE);
                tv_first_contact.setText("" + numbersSelected.get(0));
                ll_second_contact.setVisibility(View.VISIBLE);
                tv_second_contact.setText("" + numbersSelected.get(1));
                tv_more.setVisibility(View.VISIBLE);
            } else {
                ll_first_contact.setVisibility(View.GONE);
                ll_second_contact.setVisibility(View.GONE);
                tv_more.setVisibility(View.GONE);
            }
        }
    }


    @OnClick(R.id.tv_first_contact_remove)
    void removeFirstContact() {
        numbersSelected.remove(0);
        updateData();
    }

    @OnClick(R.id.tv_first_second_remove)
    void removeSecondContact() {
        numbersSelected.remove(1);
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

        ContactsAdapter mAdapter = new ContactsAdapter(mParent, newList);
        ll_contacts.setAdapter(mAdapter);

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


    @OnClick({R.id.tv_date, R.id.et_date})
    void dateSelection() {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(mParent, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                int month = monthOfYear + 1;
                et_date.setText("" + year + "-" + month + "-" + dayOfMonth);

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.show();

    }

    @OnClick({R.id.tv_time, R.id.et_time})
    void timeSelection() {
        Calendar c = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(mParent, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                et_time.setText(selectedHour + ":" + selectedMinute);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
        timePickerDialog.show();
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
        PrivateSelectedContactsAdapter privateSelectedContactsAdapter = new PrivateSelectedContactsAdapter(mParent, numbersSelected);
        listView.setAdapter(privateSelectedContactsAdapter);

        mDialog.show();

    }

    @OnClick(R.id.btn_submit)
    void submitData() {
        if (isValidForSms()) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.PURPOSE, et_reason.getText().toString());
            bundle.putString(Constants.DATE, et_date.getText().toString());
            bundle.putString(Constants.TIME, et_time.getText().toString());
            bundle.putString(Constants.LOCATION, "");
            bundle.putStringArrayList(Constants.CONTACTS, numbersSelected);
            Utility.navigateDashBoardFragment(new PartyInviteResultFragment(),
                    PartyInviteResultFragment.TAG, bundle, mParent);
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

    private boolean isValidForSms() {
        boolean isValid = true;
        if (numbersSelected != null & numbersSelected.size() <= 0) {
            Utility.showToastMessage(mParent, "Please add at least one number");
            isValid = false;
        } else if (et_date.getText().toString().length() <= 0) {
            Utility.showToastMessage(mParent, "Please select date");
            isValid = false;
        } else if (et_time.getText().toString().length() <= 0) {
            Utility.showToastMessage(mParent, "Please select time");
            isValid = false;
        } else if (Utility.isValueNullOrEmpty(et_reason.getText().toString())) {
            Utility.showToastMessage(mParent, "Please enter purpose");
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void updatedData(ArrayList<String> mContacts) {
        numbersSelected = mContacts;
        updateData();
    }

    @Override
    public void updatedData(String mContact) {
        if (numbersSelected == null) {
            numbersSelected = new ArrayList<>();
        }
        if (!numbersSelected.contains(mContact))
            numbersSelected.add(mContact);
        else
            Utility.showToastMessage(mParent, "Number Already added");
        updateData();
    }
}
