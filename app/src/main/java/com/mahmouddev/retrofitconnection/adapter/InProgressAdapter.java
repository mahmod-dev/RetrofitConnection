package com.mahmouddev.retrofitconnection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmouddev.retrofitconnection.R;
import com.mahmouddev.retrofitconnection.models.TodoResponse;

import java.util.ArrayList;

public class InProgressAdapter extends RecyclerView.Adapter<InProgressAdapter.ViewHolder> {
    ArrayList<TodoResponse> data;

    OnItemClickListener mListener;

    public InProgressAdapter(ArrayList<TodoResponse> data) {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_todo_completed, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(data.get(i).getTitle());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;


        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }

}












