package com.example.calltest.red5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PublisherFragment();
            case 1:
                return new SubscriberFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
