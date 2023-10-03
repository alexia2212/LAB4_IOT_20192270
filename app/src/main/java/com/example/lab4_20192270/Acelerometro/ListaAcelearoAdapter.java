package com.example.lab4_20192270.Acelerometro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4_20192270.DTO.ImageProfile;
import com.example.lab4_20192270.DTO.User;
import com.example.lab4_20192270.R;

import java.util.ArrayList;
import java.util.List;

public class ListaAcelearoAdapter extends RecyclerView.Adapter<ListaAcelearoAdapter.AcelerometroViewHolder>{
    private List<User> listaAceler;
    private Context context;
    private PersonasAcelerometroVM personasAcelerometroVM;
    @NonNull
    @Override
    public AcelerometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new AcelerometroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcelerometroViewHolder holder, int position){

        User user = listaAceler.get(position);
        holder.user = user;
        ImageView fotoPerfil = holder.itemView.findViewById(R.id.imagenPerfil);
        TextView genero = holder.itemView.findViewById(R.id.textViewGender);
        TextView phone = holder.itemView.findViewById(R.id.textViewPhone);
        TextView email = holder.itemView.findViewById(R.id.textViewEmail);
        TextView nombre = holder.itemView.findViewById(R.id.textViewName);
        TextView ciudad = holder.itemView.findViewById(R.id.textViewCity);
        TextView pais = holder.itemView.findViewById(R.id.textViewCountry);
        ImageView eliminarImagen = holder.itemView.findViewById(R.id.imageView2);


        Glide.with(context).load(user.getPicture().getLarge()).into(fotoPerfil);
        genero.setText("Género: "+user.getGender());
        phone.setText("Phone: "+ user.getPhone());
        email.setText("Email: "+user.getEmail());
        ciudad.setText("Ciudad: "+user.getLocation().getCity());
        pais.setText("País: "+user.getLocation().getCountry());
        nombre.setText(user.getName().getTitle()+" "+user.getName().getFirst()+" "+user.getName().getLast());
        eliminarImagen.setOnClickListener(view -> {
            ArrayList<User> listaActual = personasAcelerometroVM.getListaPersonasAcelero().getValue();
            listaActual.remove(user);
            personasAcelerometroVM.getListaPersonasAcelero().postValue(listaActual);
            notifyDataSetChanged();
        });

    }
    public class AcelerometroViewHolder extends RecyclerView.ViewHolder{
        User user;
        public AcelerometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    @Override
    public int getItemCount(){
        return listaAceler.size();
    }
    public List<User> getListaAceler(){
        return listaAceler;
    }

    public void setListaAceler(List<User> listaAceler){
        this.listaAceler = listaAceler;
    }
    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public PersonasAcelerometroVM getPersonasAcelerometroVM() {
        return personasAcelerometroVM;
    }

    public void setPersonasAcelerometroVM(PersonasAcelerometroVM personasAcelerometroVM) {
        this.personasAcelerometroVM = personasAcelerometroVM;
    }
}
