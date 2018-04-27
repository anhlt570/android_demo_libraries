package com.demo.sensors;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class NavigationManager {
    private AppCompatActivity activity;
    private FragmentManager fragmentManager;
    @IdRes
    private int placeHolder;

    public NavigationManager(AppCompatActivity activity, @IdRes int placeHolder) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
        this.placeHolder = placeHolder;
    }

    public void addPage(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack("")
                .replace(placeHolder, fragment).commit();
    }

    public void addPageCleanBackStack(Fragment fragment) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStackImmediate();
            }
        }
        addPage(fragment);
    }

    public Fragment getActiveFragment() {
        return fragmentManager.findFragmentById(placeHolder);
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
