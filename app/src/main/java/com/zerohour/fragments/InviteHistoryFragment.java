package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;
import com.zerohour.adapters.InviteHistoryAdapter;
import com.zerohour.model.InviteHistoryModel;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InviteHistoryFragment extends Fragment implements AdapterView.OnItemClickListener {
    private DashBoardActivity mParent;
    private View view;

    @BindView(R.id.ll_invite_history)
    ListView llInviteHistory;
    ArrayList<InviteHistoryModel> inviteHistoryModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashBoardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_invite_history, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        InviteHistoryAdapter inviteHistoryAdapter = new InviteHistoryAdapter(mParent, getInviteHistoryData());
        llInviteHistory.setAdapter(inviteHistoryAdapter);
       // llInviteHistory.setOnItemClickListener(this);
    }

    private ArrayList<InviteHistoryModel> getInviteHistoryData() {
        inviteHistoryModels = new ArrayList<>();

        InviteHistoryModel inviteHistoryModel = new InviteHistoryModel();
        inviteHistoryModel.setMonth("May");
        inviteHistoryModel.setDay("23");
        inviteHistoryModel.setPurpose("Birthday Function, Come a celebrate...!");
        inviteHistoryModels.add(inviteHistoryModel);

        InviteHistoryModel inviteHistoryModel2 = new InviteHistoryModel();
        inviteHistoryModel2.setMonth("May");
        inviteHistoryModel2.setDay("29");
        inviteHistoryModel2.setPurpose("My Daughter 1st Birthday");
        inviteHistoryModels.add(inviteHistoryModel2);

        InviteHistoryModel inviteHistoryModel3 = new InviteHistoryModel();
        inviteHistoryModel3.setMonth("Jun");
        inviteHistoryModel3.setDay("19");
        inviteHistoryModel3.setPurpose("My Marriage day");
        inviteHistoryModels.add(inviteHistoryModel3);
        return inviteHistoryModels;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.INVITE_HISTORY, inviteHistoryModels.get(position));
        Utility.navigateDashBoardFragment(new InviteHistoryDetailFragment(),
                InviteHistoryDetailFragment.TAG, bundle, mParent);
    }
}
