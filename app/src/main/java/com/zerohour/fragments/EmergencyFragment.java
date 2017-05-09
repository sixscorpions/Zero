package com.zerohour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class EmergencyFragment extends Fragment {

    public static final String TAG = EmergencyFragment.class.getSimpleName();
    private MainActivity mParent;
    private View view;

    @BindView(R.id.tv_panic_image)
    TextView tvPanicImage;
    @BindView(R.id.tv_panic)
    TextView tvPanic;
    @BindView(R.id.tv_fire_image)
    TextView tvFireImage;
    @BindView(R.id.tv_fire)
    TextView tvFire;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_emergency, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        tvPanicImage.setTypeface(Utility.getFontAwesomeWebFont(mParent));
        tvFireImage.setTypeface(Utility.getFontAwesomeWebFont(mParent));
    }

    @OnClick({R.id.tv_panic_image, R.id.tv_panic})
    void sendPanic() {
        Utility.showToastMessage(mParent, "Panic Alert sent to Security on Plot No 204");
    }

    @OnClick({R.id.tv_fire_image, R.id.tv_fire})
    void sendFirAlert() {
        Utility.showToastMessage(mParent, "Fir/ Gas Alert sent to Security on Plot No 204");
    }


}
