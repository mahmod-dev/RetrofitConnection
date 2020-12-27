package com.mahmouddev.retrofitconnection.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mahmouddev.retrofitconnection.retrofit.APIClient;
import com.mahmouddev.retrofitconnection.retrofit.APIInterface;
import com.mahmouddev.retrofitconnection.R;
import com.mahmouddev.retrofitconnection.adapter.CompletedAdapter;
import com.mahmouddev.retrofitconnection.models.TodoResponse;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishedFragment extends Fragment {

    RecyclerView rv;
    ArrayList<TodoResponse> usersResponses;
    ProgressDialog progressDoalog;
    int id;

    public FinishedFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vew =  inflater.inflate(R.layout.fragment_finished, container, false);
        rv = vew.findViewById(R.id.rvFinished);
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<TodoResponse>> call = service.getTodoByUserId(id,true);
        call.enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                progressDoalog.dismiss();
                usersResponses = response.body();
                initRecycleView();


            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }

        });

        return vew;
    }

    public void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(manager);
        CompletedAdapter adapter = new CompletedAdapter(usersResponses);
        rv.setAdapter(adapter);

    }
}