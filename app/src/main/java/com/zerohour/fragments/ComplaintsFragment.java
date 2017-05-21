package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.zerohour.DashBoardActivity;
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
public class ComplaintsFragment extends Fragment {


    public static final String TAG = ComplaintsFragment.class.getSimpleName();
    private DashBoardActivity mParent;
    private View view;

    /*@BindView(R.id.spinner_category)
    Spinner spinnerCategory;*/
    @BindView(R.id.et_complaint)
    EditText etComplaint;

    @BindView(R.id.radio_button_plumbing)
    RadioButton radioButtonPlumbing;
    @BindView(R.id.radio_button_electrical)
    RadioButton radioButtonElectrical;
    @BindView(R.id.radio_button_general)
    RadioButton radioButtonGeneral;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashBoardActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_complaints, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {


    }

    @OnClick(R.id.btn_drop_complaint)
    void dropComplaint() {
        if (isValidFields()) {
            etComplaint.setText("");
            Utility.showToastMessage(mParent, "Successfully sent complaint.");
        }
    }

    private boolean isValidFields() {
        boolean isValid = true;
        if (Utility.isValueNullOrEmpty(etComplaint.getText().toString())) {
            Utility.showToastMessage(mParent, "Please write your complaint");
            isValid = false;
        }
        return isValid;
    }

}
