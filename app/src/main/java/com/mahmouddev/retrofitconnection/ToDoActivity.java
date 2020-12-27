package com.mahmouddev.retrofitconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.mahmouddev.retrofitconnection.adapter.ViewPagerFragmentAdapter;


public class ToDoActivity extends AppCompatActivity  {
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        FirebaseApp.initializeApp(this);
        String[] titles = new String[]{"In progress", "Finished"};
        getSupportActionBar().setElevation(0);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        TabLayout tab_layout = findViewById(R.id.tab_layout);

        try {
            id = getIntent().getExtras().getInt("id");

        }catch (Exception ex){
            FirebaseCrashlytics.getInstance().recordException(ex);


        }

        viewPager2.setAdapter(new ViewPagerFragmentAdapter(this,id));
        // attaching tab mediator
        new TabLayoutMediator(tab_layout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
    }


}