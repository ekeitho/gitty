package com.ekeitho.github2.binding;

import android.databinding.BindingAdapter;
import android.widget.LinearLayout;


public class ListViewBindings {
    @BindingAdapter("android:onClick")
    public static void doSomethingDiffernt(LinearLayout layout, int value) {
        layout.setBackgroundColor(value);
    }
}
