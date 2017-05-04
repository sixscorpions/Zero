package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartyInviteFragment extends Fragment {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            if (numbersSelected != null && numbersSelected.size() == 1) {
                etMobileNumber.setText("");
                ll_first_contact.setVisibility(View.VISIBLE);
                tv_first_contact.setText("" + numbersSelected.get(0));
            } else if (numbersSelected != null && numbersSelected.size() == 2) {
                etMobileNumber.setText("");
                ll_first_contact.setVisibility(View.VISIBLE);
                tv_first_contact.setText("" + numbersSelected.get(0));
                ll_second_contact.setVisibility(View.VISIBLE);
                tv_second_contact.setText("" + numbersSelected.get(1));
                tv_more.setVisibility(View.GONE);
            } else {
                etMobileNumber.setText("");
                ll_first_contact.setVisibility(View.VISIBLE);
                tv_first_contact.setText("" + numbersSelected.get(0));
                ll_second_contact.setVisibility(View.VISIBLE);
                tv_second_contact.setText("" + numbersSelected.get(1));
                tv_more.setVisibility(View.VISIBLE);
            }
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
}
