package com.zerohour.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.adapters.PrivateSelectedContactsAdapter;
import com.zerohour.interfaces.IUpdateDialogData;
import com.zerohour.interfaces.IUpdateNumberData;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartyInviteFragment extends Fragment implements IUpdateDialogData, IUpdateNumberData {

    private MainActivity mParent;
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

    @BindView(R.id.tv_more)
    TextView tv_more;

    private ArrayList<String> numbersSelected;
    public static Dialog mDialog;
    private static IUpdateDialogData iUpdateDialogData;
    private static IUpdateNumberData iUpdateNumberData;

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
        mParent = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK,
                Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        mParent.startActivityForResult(pickContactIntent, Constants.RESULT_PICK_CONTACT);
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
