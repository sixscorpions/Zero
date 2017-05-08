package com.zerohour;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zerohour.fragments.AlienCarFragment;
import com.zerohour.fragments.InviteFragment;
import com.zerohour.fragments.MaidStatusFragment;
import com.zerohour.fragments.NoticeBoardFragment;
import com.zerohour.fragments.PartyInviteFragment;
import com.zerohour.utils.Constants;
import com.zerohour.utils.Utility;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new InviteFragment();
                title = getString(R.string.nav_item_invite);
                break;
            case 1:
                fragment = new MaidStatusFragment();
                title = getString(R.string.nav_item_maid_status);
                break;
            case 2:
                fragment = new AlienCarFragment();
                title = getString(R.string.nav_item_alien_car);
                break;
            case 3:
                fragment = new NoticeBoardFragment();
                title = getString(R.string.nav_item_notice_board);
                break;
            case 5:
                fragment = new NoticeBoardFragment();
                title = getString(R.string.nav_item_complaints);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Utility.showToastMessage(this, "Failed to pick contact");
        }
    }

    private void contactPicked(Intent data) {
        Uri contactUri = data.getData();
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = MainActivity.this.getContentResolver().query(
                contactUri, projection, null, null, null);
        cursor.moveToFirst();
        int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        String number = cursor.getString(column);
        number = number.replace(" ", "");
        number = number.replace("-", "");
        number = number.replace("+91", "");
        //Utility.showToastMessage(this, "" + number);
        PartyInviteFragment.getInstanceData().updatedData(number);
    }
}