package com.moonbear.carmarz.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moonbear.carmarz.ride.account.AccountFragment;
import com.moonbear.carmarz.ride.HomeFragment;
import com.moonbear.carmarz.ride.payment.PaymentFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        }
        if (position == 1) {
            return new PaymentFragment();
        }
        if (position == 2) {
            return new AccountFragment();
        }
        return new HomeFragment();
    }

    public int getCount() {
        return 3;
    }

}
