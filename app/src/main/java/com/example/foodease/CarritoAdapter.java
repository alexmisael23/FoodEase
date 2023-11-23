package com.example.foodease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private List<comidas> carritoItems;
    private OnItemClickListener listener;

    // Constructor que recibe la lista de elementos del carrito
    public CarritoAdapter(List<comidas> carritoItems, OnItemClickListener listener) {
        this.carritoItems = carritoItems;
        this.listener = listener;  // Inicializa el listener
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_carrito, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        comidas item = carritoItems.get(position);

        // Configurar los TextViews con la información del elemento del carrito
        holder.textViewNombre.setText(item.getNombreComida());
        holder.textViewPrecio.setText(String.valueOf(item.getPrecio()));
        holder.textViewcantidad.setText(String.valueOf(item.getCantidad()));
        holder.textViewtipo.setText(item.getTipoComida());
        // Añadir más configuraciones según tus necesidades

        //Configurar el click del boton de eliminacion
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteItemClick(position);
                }
            }
        });
    }

    //Interfaz para manejar eventos de click
    public interface OnItemClickListener {
        void onDeleteItemClick(int position);
    }


    @Override
    public int getItemCount() {
        return carritoItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewtipo;
        TextView textViewNombre;
        TextView textViewPrecio;
        TextView textViewcantidad;

        ImageButton btnEliminar;
        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewnombre);
            textViewPrecio = itemView.findViewById(R.id.textViewprecio);
            textViewtipo = itemView.findViewById(R.id.textViewtipo);
            textViewcantidad = itemView.findViewById(R.id.textViewcantidad);// Añadir más TextViews según tus necesidades

            //Configura el elemento de click para el boton de eliminación
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteItemClick(position);
                        }
                    }
                }
            });
        }
    }
}