package com.example.bluetoothdemo.blue_tooth_control_panel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class BluetoothControlPanelActivity_ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    BluetoothControlPanelActivity_ViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        String result = "";
        switch(position){
            case 0:
                result = "聊天";
                break;
            case 1:
                result = "键盘";
                break;
            case 2:
                result = "心电图";
                break;
        }
        return result;
    }
}
