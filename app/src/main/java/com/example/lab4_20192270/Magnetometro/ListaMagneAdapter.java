package com.example.lab4_20192270.Magnetometro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4_20192270.DTO.User;
import com.example.lab4_20192270.R;

import java.util.ArrayList;
import java.util.List;

public class ListaMagneAdapter extends RecyclerView.Adapter<ListaMagneAdapter.MagnetometroViewHolder>{
    private List<User> listaMagnet;
    private PersonasMagneViewModel personasMagneViewModel;
    private Context context;

    public class MagnetometroViewHolder extends RecyclerView.ViewHolder{
        User user;
        public MagnetometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
    @NonNull
    @Override
    public ListaMagneAdapter.MagnetometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new ListaMagneAdapter.MagnetometroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MagnetometroViewHolder holder, int position) {

        User user = listaMagnet.get(position);
        holder.user = user;
        ImageView fotoPerfil = holder.itemView.findViewById(R.id.imagenPerfil);
        TextView genero = holder.itemView.findViewById(R.id.textViewGender);
        TextView phone = holder.itemView.findViewById(R.id.textViewPhone);
        TextView name = holder.itemView.findViewById(R.id.textViewName);
        TextView email = holder.itemView.findViewById(R.id.textViewEmail);
        TextView ciudad = holder.itemView.findViewById(R.id.textViewCity);
        TextView pais = holder.itemView.findViewById(R.id.textViewCountry);
        ImageView eliminarPersona = holder.itemView.findViewById(R.id.imageView2);
        Glide.with(context).load(user.getPicture().getLarge()).into(fotoPerfil);

        name.setText(user.getName().getTitle()+" "+user.getName().getFirst()+" "+user.getName().getLast());
        genero.setText("Género: "+user.getGender());
        phone.setText("Phone: "+ user.getPhone());
        email.setText("Email: "+user.getEmail());
        ciudad.setText("Ciudad: "+user.getLocation().getCity());
        pais.setText("País: "+user.getLocation().getCountry());
        eliminarPersona.setOnClickListener(view -> {
            ArrayList<User> listaActual = personasMagneViewModel.getListaPersonasMagne().getValue();
            listaActual.remove(user);
            personasMagneViewModel.getListaPersonasMagne().postValue(listaActual);
            notifyDataSetChanged();
        });

    }
    @Override
    public int getItemCount() {
        return listaMagnet.size();
    }

    public List<User> getListaMagnet() {
        return listaMagnet;
    }

    public void setListaMagnet(List<User> listaMagnet) {
        this.listaMagnet = listaMagnet;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
