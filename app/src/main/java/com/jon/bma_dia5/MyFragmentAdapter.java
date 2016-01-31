package com.jon.bma_dia5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 29/01/16. Adaptador per asociar els fragments al viewPager
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    // Recomanacio, afegir els fragments a un array list (son mes facils de manejar)
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(Fragment0.newInstance());     // posicio 0
        titles.add("Fragment 0");

        fragments.add(Fragment1.newInstance());     // posicio 1
        titles.add("Fragment 1");

        fragments.add(Fragment2.newInstance());
        titles.add("Fragment 2");
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
