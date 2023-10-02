package com.example.lab4_20192270;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20192270.DTO.User;

import java.util.List;

public class Adaptador extends  RecyclerView.Adapter<Adaptador.UsuariosViewHolder>{
    private List<User> listaUsuarios;
    private Context context;

    public class UsuariosViewHolder extends RecyclerView.ViewHolder{
        User user;
        public UsuariosViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate((R.layout.item_usuario), parent, false);
        return new UsuariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull UsuariosViewHolder holder, int position){

        User usuario = listaUsuarios.get(position);
        holder.user = usuario;

        TextView textViewTitle = holder.itemView.findViewById(R.id.textViewName);
        textViewTitle.setText(usuario.getName().getTitle() + usuario.getName().getFirst() + usuario.getName().getLast());

        TextView textViewGender = holder.itemView.findViewById(R.id.textViewGender);
        textViewGender.setText(usuario.getGender());

        TextView textViewCiudad = holder.itemView.findViewById(R.id.textViewCity);
        textViewCiudad.setText(usuario.getLocation().getCity());

        TextView textViewPais = holder.itemView.findViewById(R.id.textViewCountry);
        textViewPais.setText(usuario.getLocation().getCountry());

        TextView textViewEmail = holder.itemView.findViewById(R.id.textViewEmail);
        textViewEmail.setText(usuario.getEmail());

        TextView textViewPhone = holder.itemView.findViewById(R.id.textViewPhone);
        textViewPhone.setText(usuario.getPhone());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

}
