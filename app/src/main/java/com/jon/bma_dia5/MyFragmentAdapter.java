package com.jon.bma_dia5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(Fragment_login.newInstance());     // posicio 0
        titles.add("Login");

        fragments.add(Fragment_textview.newInstance());     // posicio 1
        titles.add("Check");

        fragments.add(Fragment_spinner.newInstance());
        titles.add("Spinner");

        fragments.add(Fragment_crono.newInstance());
        titles.add("Crono");

        fragments.add(Fragment_textview.newInstance());     // posicio 1
        titles.add("Check");

        fragments.add(Fragment_textview.newInstance());     // posicio 1
        titles.add("Check");

        fragments.add(Fragment_textview.newInstance());     // posicio 1
        titles.add("Check");

        fragments.add(Fragment_textview.newInstance());     // posicio 1
        titles.add("Check");
    }


    // Per renombrar els tituls del tab
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
