package com.digitalmeverick.stupa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class DynamicFragmentAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    DynamicFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    // get the current item with position number
    @Override
    public Fragment getItem(int position) {

        Fragment fragment=null;

        //add a case statemnt equal to number of tabs we have 4

        switch (position){
            case 0:
                Bundle b = new Bundle();
                b.putInt("position", position);
                fragment = DynamicFragment.newInstance();
                fragment.setArguments(b);
                break;

            case 1:
                fragment = new Fragment1();
                break;
            case 2:
                Bundle c = new Bundle();
                c.putInt("position", position);
                fragment = DynamicFragment.newInstance();
                fragment.setArguments(c);
                break;
            case 3:
                Bundle d = new Bundle();
                d.putInt("position", position);
                fragment = DynamicFragment.newInstance();
                fragment.setArguments(d);
                break;
        }
        return fragment;
    }

    // get total number of tabs
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

