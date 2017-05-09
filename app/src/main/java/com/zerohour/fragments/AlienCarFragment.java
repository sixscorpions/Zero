package com.zerohour.fragments;


import android.os.Bundle;
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
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlienCarFragment extends Fragment {


    public static final String TAG = AlienCarFragment.class.getSimpleName();
    private MainActivity mParent;
    private View view;

    @BindView(R.id.et_blocking_car_number)
    EditText etBlockingCarNumber;
    @BindView(R.id.tv_details)
    TextView tvDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alien_car, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {

    }

    @OnClick(R.id.btn_get_details)
    void getDetails() {
        if (isValidFields()) {
            etBlockingCarNumber.setText("");
            tvDetails.setText("Vehicle belong to Flat No: 61101, Mr Chakravarthi Phone 7799003321");
        }
    }

    private boolean isValidFields() {
        boolean isValid = true;
        if (Utility.isValueNullOrEmpty(etBlockingCarNumber.getText().toString())) {
            Utility.showToastMessage(mParent, "Please enter blocking car number");
            isValid = false;
        }
        return isValid;
    }
}
