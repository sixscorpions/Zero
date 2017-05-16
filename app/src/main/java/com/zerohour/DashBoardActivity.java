package com.zerohour;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zerohour.customviews.SlidingLayout;
import com.zerohour.fragments.AlienCarFragment;
import com.zerohour.fragments.ComplaintsFragment;
import com.zerohour.fragments.EmergencyFragment;
import com.zerohour.fragments.HomeFragment;
import com.zerohour.fragments.InviteFragment;
import com.zerohour.fragments.MaidStatusFragment;
import com.zerohour.fragments.NoticeBoardFragment;
import com.zerohour.utils.Utility;

public class DashBoardActivity extends AppCompatActivity {
    // The SlidingLayout which will hold both the sliding menu and our main content
    // Main content will holds our Fragment respectively
    SlidingLayout slidingLayout;

    // ListView menu
    private ListView listMenu;
    private String[] listMenuItems;

    private Toolbar toolbar;
    private TextView title; //page title
    private ImageView btMenu; // Menu button
    private Fragment currentFragment;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the mainLayout
        setContentView(R.layout.activity_dash_board);
        slidingLayout = (SlidingLayout) findViewById(R.id.sliding_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);

        // Init menu
        listMenuItems = getResources().getStringArray(R.array.nav_drawer_labels);
        listMenu = (ListView) findViewById(R.id.activity_main_menu_listview);
        listMenu.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listMenuItems));
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onMenuItemClick(parent, view, position, id);
            }

        });

        // handling menu button event
        btMenu = (ImageView) findViewById(R.id.menu_icon);
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show/hide the menu
                toggleMenu(v);
            }
        });

        // Replace fragment main when activity start
        FragmentManager fm = DashBoardActivity.this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        ft.add(R.id.activity_main_content_fragment, fragment);
        ft.commit();

        currentFragment = fragment;
        title.setText(Utility.getResourcesString(this, R.string.app_name));
    }

    public void toggleMenu(View v) {
        slidingLayout.toggleMenu();
    }

    // Perform action when a menu item is clicked
    private void onMenuItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.nav_item_home);
                break;
            case 1:
                fragment = new InviteFragment();
                title = getString(R.string.nav_item_invite);
                break;
            case 2:
                fragment = new MaidStatusFragment();
                title = getString(R.string.nav_item_maid_status);
                break;
            case 3:
                fragment = new AlienCarFragment();
                title = getString(R.string.nav_item_alien_car);
                break;
            case 4:
                fragment = new NoticeBoardFragment();
                title = getString(R.string.nav_item_notice_board);
                break;
            case 5:
                fragment = new ComplaintsFragment();
                title = getString(R.string.nav_item_complaints);
                break;
            case 6:
                fragment = new EmergencyFragment();
                title = getString(R.string.nav_item_emergency);
                break;
            default:
                break;
        }

        if (!fragment.getClass().equals(currentFragment.getClass())) {
            // Replace current fragment by this new one
            ft.replace(R.id.activity_main_content_fragment, fragment);
            ft.commit();

            currentFragment = fragment;
        }

        // Hide menu anyway
        slidingLayout.toggleMenu();

    }

    @Override
    public void onBackPressed() {
        if (slidingLayout.isMenuShown()) {
            slidingLayout.toggleMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setTitle("");
    }

}
