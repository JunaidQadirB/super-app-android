package com.moonbear.carmarz.codeclasses;



import androidx.fragment.app.Fragment;

import com.moonbear.carmarz.Interface.OnBackPressListener;


public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImplementation(this).onBackPressed();
    }
}