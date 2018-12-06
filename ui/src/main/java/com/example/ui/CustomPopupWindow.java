package com.example.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class CustomPopupWindow extends PopupWindow {
    public CustomPopupWindow(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.layout_popup_window,null),
                ViewGroup.LayoutParams.MATCH_PARENT,
                300,
                false);
    }
}
