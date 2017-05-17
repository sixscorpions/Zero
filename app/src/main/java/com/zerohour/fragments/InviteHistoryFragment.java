package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerohour.DashBoardActivity;
import com.zerohour.R;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InviteHistoryFragment extends Fragment {
    private DashBoardActivity mParent;
    private View view;

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


    }


}
