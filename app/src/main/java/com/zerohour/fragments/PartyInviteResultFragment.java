package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zerohour.DashBoardActivity;
import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartyInviteResultFragment extends Fragment {

    public static final String TAG = PartyInviteResultFragment.class.getSimpleName();
    private DashBoardActivity mParent;
    private View view;
    private Bundle bundle;

    private String purpose = "";
    private String date = "";
    private String time = "";
    private ArrayList<String> contacts;

    @BindView(R.id.tv_message)
    TextView tvMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashBoardActivity) getActivity();
        bundle = getArguments();
        if (bundle != null) {
            purpose = bundle.getString(Constants.PURPOSE);
            date = bundle.getString(Constants.DATE);
            time = bundle.getString(Constants.TIME);
            contacts = bundle.getStringArrayList(Constants.CONTACTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_party_invite_result, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        String finalMessage = Utility.getResourcesString(mParent, R.string.message) + " " + purpose + " on Date: " + date + " Time " + time + "At Harshita apartments";
        tvMessage.setText("" + finalMessage);
    }

    @OnClick(R.id.btn_send_invitation)
    void sendInvitation() {
        Utility.showToastMessage(mParent, "All the messages are sent");
    }
}
