package com.ludowica.goodlooks.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.adapter.TabAdapter;
import com.ludowica.goodlooks.fragment.HomeFragment;
import com.ludowica.goodlooks.fragment.CategoryFragment;
import com.ludowica.goodlooks.fragment.AccountFragment;

public class HomeActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new CategoryFragment(), "Categories");
        adapter.addFragment(new AccountFragment(), "Account");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}