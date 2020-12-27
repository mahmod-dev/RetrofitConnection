package com.mahmouddev.retrofitconnection.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mahmouddev.retrofitconnection.util.MyPreferences;
import com.mahmouddev.retrofitconnection.R;

public class ProfileFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        MyPreferences.setContext(getContext());
        TextView tvProfileName = view.findViewById(R.id.tvProfileName);
        TextView tvProfileMobile = view.findViewById(R.id.tvProfileMobile);
        TextView tvEmail = view.findViewById(R.id.tvEmail);

        tvProfileName.setText(MyPreferences.getStr("name"));
        tvEmail.setText(MyPreferences.getStr("email"));
        tvProfileMobile.setText(MyPreferences.getStr("mobile"));

        return view;
    }
}