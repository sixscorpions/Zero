package com.zerohour.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @BindView(R.id.tv_first_contact)
    TextView tv_first_contact;
    @BindView(R.id.tv_first_contact_remove)
    TextView tv_first_contact_remove;

    @BindView(R.id.tv_second_contact)
    TextView tv_second_contact;
    @BindView(R.id.tv_first_second_remove)
    TextView tv_first_second_remove;


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
        tvContactsImage.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_add_image.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_date.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_time.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_first_contact_remove.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tv_first_second_remove.setTypeface(Utility.getMaterialIconsRegular(mParent));
    }


}
