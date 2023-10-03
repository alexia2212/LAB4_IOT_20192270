package com.example.lab4_20192270.Magnetometro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20192270.DTO.User;

import java.util.List;

public class ListaMagneAdapter extends RecyclerView.Adapter<ListaMagneAdapter.MagnetometroViewHolder>{
    private List<User> listaMagnet;
    private Context context;

    public class MagnetometroViewHolder extends RecyclerView.ViewHolder{
        User user;
        public MagnetometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
    @NonNull
    @Override
    public MagnetometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MagnetometroViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return listaMagnet.size();
    }
}
