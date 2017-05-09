package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerohour.MainActivity;
import com.zerohour.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeBoardDetailFragment extends Fragment {

    public static final String TAG = NoticeBoardDetailFragment.class.getSimpleName();
    private MainActivity mParent;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notice_board_detail, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {

    }


}
