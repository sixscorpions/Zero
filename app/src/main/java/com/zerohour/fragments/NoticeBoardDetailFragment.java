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
import com.zerohour.model.NoticeBoardItem;
import com.zerohour.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeBoardDetailFragment extends Fragment {

    public static final String TAG = NoticeBoardDetailFragment.class.getSimpleName();
    private DashBoardActivity mParent;
    private View view;

    private NoticeBoardItem noticeBoardItem;


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_message)
    TextView tvMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashBoardActivity) getActivity();
        noticeBoardItem = (NoticeBoardItem) getArguments().getSerializable(Constants.INVITE_HISTORY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notice_board_detail, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        tvTitle.setText(noticeBoardItem.getTitle());
        tvMessage.setText(noticeBoardItem.getMessage());
    }


}
