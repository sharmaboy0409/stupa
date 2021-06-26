package com.digitalmeverick.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {//this is dashboard also
    private ViewPager viewPager;
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

    }

    private void initViews() {

        viewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);


        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setDynamicFragmentToTabLayout();
    }

    private void setDynamicFragmentToTabLayout() {

        
        // you can add any number of tab
        mTabLayout.addTab(mTabLayout.newTab().setText("Page:ONE " ));
        mTabLayout.addTab(mTabLayout.newTab().setText("Page:TWO " ));
        mTabLayout.addTab(mTabLayout.newTab().setText("Page:THREE " ));
        mTabLayout.addTab(mTabLayout.newTab().setText("Page:four " ));

        DynamicFragmentAdapter mDynamicFragmentAdapter = new DynamicFragmentAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());


        viewPager.setAdapter(mDynamicFragmentAdapter);

       //pahli baar chalegi //home fragment
        viewPager.setCurrentItem(1);
    }
}