package com.moonbear.carmarz.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.moonbear.carmarz.ride.history.HistoryFragment;
import com.moonbear.carmarz.ride.history.ScheduledFragment;


public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new ScheduledFragment();
        } else if (position == 1) {
            fragment = new HistoryFragment();
        }
        return fragment;
    }


    @Override
    public int getItemCount() {
        return 2;
    }

}
