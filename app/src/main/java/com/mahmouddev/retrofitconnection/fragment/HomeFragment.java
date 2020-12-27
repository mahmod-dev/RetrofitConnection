package com.mahmouddev.retrofitconnection.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.mahmouddev.retrofitconnection.retrofit.APIClient;
import com.mahmouddev.retrofitconnection.retrofit.APIInterface;
import com.mahmouddev.retrofitconnection.R;
import com.mahmouddev.retrofitconnection.ToDoActivity;
import com.mahmouddev.retrofitconnection.adapter.CustomRecycleAdapter;
import com.mahmouddev.retrofitconnection.models.UsersResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView rv;
    ArrayList<UsersResponse> usersResponses;
    ProgressDialog progressDoalog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vew = inflater.inflate(R.layout.fragment_home, container, false);
        rv = vew.findViewById(R.id.rvHome);
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<UsersResponse>> call = service.getUsers();
        call.enqueue(new Callback<ArrayList<UsersResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UsersResponse>> call, Response<ArrayList<UsersResponse>> response) {
                progressDoalog.dismiss();
                usersResponses = response.body();
                initRecycleView();


            }

            @Override
            public void onFailure(Call<ArrayList<UsersResponse>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                FirebaseCrashlytics.getInstance().recordException(t);
            }

        });

        return vew;
    }

    public void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(manager);
        CustomRecycleAdapter adapter = new CustomRecycleAdapter(usersResponses);
        rv.setAdapter(adapter);

        adapter.setOnClickListener(id -> {
            Intent intent = new Intent(getContext(), ToDoActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);

        });

    }
}