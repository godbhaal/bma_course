package com.jon.bma_dia5;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 29/01/16. Adaptador per asociar els fragments al viewPager
 */
//public class MyFragmentAdapter extends FragmentPagerAdapter {
public class MyFragmentAdapter extends FragmentPagerAdapter {

    // Recomanacio, afegir els fragments a un array list (son mes facils de manejar)
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    Context context;

    private int[] icon = {
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.ic_menu_manage,
            R.drawable.ic_menu_send,
            R.drawable.ic_menu_slideshow,
    };

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        fragments.add(Fragment_login.newInstance());     // posicio 0
        titles.add("Login");

        fragments.add(Fragment_textview.newInstance());     // posicio 1
        titles.add("Check");

        fragments.add(Fragment_spinner.newInstance());
        titles.add("Spinner");

        fragments.add(Fragment_crono.newInstance());
        titles.add("Crono");

        fragments.add(Fragment_login.newInstance());     // posicio 0
        titles.add("Login");
    }


    // Per renombrar els tituls del tab
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles.get(position);
//    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        Drawable image = context.getResources().getDrawable(icon[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString("   " + titles.get(position));
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}