package com.zerohour.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.utils.Utility;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventInviteFragment extends Fragment {


    public static final String TAG = EventInviteFragment.class.getSimpleName();
    private DashBoardActivity mParent;
    private View view;

    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_reason)
    EditText etReason;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashBoardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_invite, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        tvDate.setTypeface(Utility.getMaterialIconsRegular(mParent));
    }

    @OnClick(R.id.btn_submit)
    void submitData() {
        if (isValidFields()) {
            etDate.setText("");
            etReason.setText("");
            Utility.showToastMessage(mParent, "Message Delivered to Security");
        }
    }

    @OnClick({R.id.tv_date, R.id.et_date})
    void dateSelection() {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(mParent, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                int month = monthOfYear + 1;
                etDate.setText("" + year + "-" + month + "-" + dayOfMonth);

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.show();

    }

    private boolean isValidFields() {
        boolean isValid = true;
        if (Utility.isValueNullOrEmpty(etDate.getText().toString())) {
            Utility.showToastMessage(mParent, "Please enter date");
            isValid = false;
        } else if (Utility.isValueNullOrEmpty(etReason.getText().toString())) {
            Utility.showToastMessage(mParent, "Please enter purpose");
            isValid = false;
        }
        return isValid;
    }

}
