package com.zerohour.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zerohour.MainActivity;
import com.zerohour.R;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaidStatusFragment extends Fragment {

    public static final String TAG = MaidStatusFragment.class.getSimpleName();
    private MainActivity mParent;
    private View view;

    @BindView(R.id.tv_face_image)
    TextView tvFaceImage;

    @BindView(R.id.tv_call_image)
    TextView tvCallImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maid_status, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        tvFaceImage.setTypeface(Utility.getMaterialIconsRegular(mParent));
        tvCallImage.setTypeface(Utility.getMaterialIconsRegular(mParent));
        askPermission();
    }

    private void askPermission() {
        if (ContextCompat.checkSelfPermission(mParent,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mParent,
                    Manifest.permission.CALL_PHONE)) {

            } else {

                ActivityCompat.requestPermissions(mParent,
                        new String[]{Manifest.permission.CALL_PHONE},
                        Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }
    }

    @OnClick(R.id.tv_call_image)
    void callMaid() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "7799920206"));
        startActivity(intent);
    }

}
