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
import com.zerohour.adapters.NoticeBoardItemAdapter;
import com.zerohour.model.NoticeBoardItem;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeBoardFragment extends Fragment implements AdapterView.OnItemClickListener {


    public static final String TAG = NoticeBoardFragment.class.getSimpleName();
    private DashBoardActivity mParent;
    private View view;

    @BindView(R.id.list_view_notice_board)
    ListView listViewNoticeBoard;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashBoardActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notice_board, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        NoticeBoardItemAdapter noticeBoardItemAdapter = new NoticeBoardItemAdapter(mParent, getNoticeBoardData());
        listViewNoticeBoard.setAdapter(noticeBoardItemAdapter);
        //listViewNoticeBoard.setOnItemClickListener(this);
    }

    private ArrayList<NoticeBoardItem> getNoticeBoardData() {
        ArrayList<NoticeBoardItem> noticeBoardItems = new ArrayList<>();

        //for (int i = 0; i < 10; i++) {
        NoticeBoardItem noticeBoardItem = new NoticeBoardItem();
        noticeBoardItem.setTitle("Kalamandir");
        noticeBoardItem.setMessage("30% discount on the saree's.  Only on this Sunday");
        noticeBoardItems.add(noticeBoardItem);

        NoticeBoardItem noticeBoardItem2 = new NoticeBoardItem();
        noticeBoardItem2.setTitle("Chadana Brothers");
        noticeBoardItem2.setMessage("10% discount on the saree's.  Only on this Wednesday");
        noticeBoardItems.add(noticeBoardItem2);

        NoticeBoardItem noticeBoardItem3 = new NoticeBoardItem();
        noticeBoardItem3.setTitle("Apartment meeting");
        noticeBoardItem3.setMessage("This Friday we have a meeting in the apartment. Please attend all");
        noticeBoardItems.add(noticeBoardItem3);
        //}
        return noticeBoardItems;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
