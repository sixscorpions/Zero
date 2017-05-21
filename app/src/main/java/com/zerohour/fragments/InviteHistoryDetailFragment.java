package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.model.InviteHistoryModel;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InviteHistoryDetailFragment extends Fragment {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        tv_month.setText("" + inviteHistoryModel.getMonth());
        tv_day.setText("" + inviteHistoryModel.getDay());
        tv_purpose.setText("" + inviteHistoryModel.getPurpose());
    }


}
