package com.mahmouddev.retrofitconnection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmouddev.retrofitconnection.R;
import com.mahmouddev.retrofitconnection.models.UsersResponse;


import java.util.ArrayList;

public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder> {
    ArrayList<UsersResponse> data;

    OnItemClickListener mListener;

    public CustomRecycleAdapter(ArrayList<UsersResponse> data) {
        this.data = data;

    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_users, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(data.get(i).getName());
        viewHolder.tvEmail.setText(data.get(i).getEmail());
        viewHolder.tvPhone.setText(data.get(i).getPhone());
        viewHolder.tvWebsite.setText(data.get(i).getWebsite());
        viewHolder.tvCity.setText(data.get(i).getAddress().getCity());
        viewHolder.tvStreet.setText(data.get(i).getAddress().getStreet());
        viewHolder.tvSuite.setText(data.get(i).getAddress().getSuite());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvWebsite;
        TextView tvCity;
        TextView tvStreet;
        TextView tvSuite;
        TextView tvPhone;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvStreet = itemView.findViewById(R.id.tvStreet);
            tvSuite = itemView.findViewById(R.id.tvSuite);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(data.get(getAdapterPosition()).getId());
                        }
                    }
                }
            });
        }
    }

}












