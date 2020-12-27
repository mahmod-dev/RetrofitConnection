package com.mahmouddev.retrofitconnection.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mahmouddev.retrofitconnection.fragment.FinishedFragment;
import com.mahmouddev.retrofitconnection.fragment.InProgressFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {
    String[] titles = new String[]{"In progress", "Finished"};
int id;
    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity,int id) {
        super(fragmentActivity);
        this.id = id;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new InProgressFragment(id);
            case 1:
                return new FinishedFragment(id);

        }
        return new InProgressFragment(id);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}