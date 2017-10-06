package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olegka on 04.10.2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    /**
     * Адаптер и создание фрагментов можно реализовать при помощи enum'ов
     */

    /*private enum GiftsTab {
        ACTIVE(R.string.my),
        INACTIVE(R.string.sent);

        private int titleId;

        GiftsTab(int titleId) {
            this.titleId = titleId;
        }

        public int getTitleId() {
            return titleId;
        }
    }*/

    /**
     * Создание фрагмента
     */
    /*
    @Override
    public Fragment getItem(int position) {
        switch (GiftsTab.values()[position]) {
            case ACTIVE:
                return new ActiveCertificatesFragment();

            case INACTIVE:
                return new InActiveCertificatesFragment();

            default:
                throw new IllegalStateException();
        }
    }
     */

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

}
