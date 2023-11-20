package com.example.foodease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class bebidaAdapter extends FirestoreRecyclerAdapter<bebidas, bebidaAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public bebidaAdapter(@NonNull FirestoreRecyclerOptions<bebidas> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull bebidaAdapter.ViewHolder holder, int position, @NonNull bebidas bebidas) {
        holder.textViewNombre.setText(bebidas.getNombreBebida());
        holder.textViewPrecio.setText(String.valueOf(bebidas.getPrecio()));
        //  holder.textViewFecha.setText(String.valueOf(comidas.getFechaCreacion()));
        holder.textViewTipo.setText(bebidas.getTipoBebida());
        holder.textViewCantidad.setText(String.valueOf(bebidas.getCantidad()));
    }

    @NonNull
    @Override
    public bebidaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_bebidas, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombre;
        TextView textViewPrecio;
        //TextView textViewFecha;
        TextView textViewTipo;
        TextView textViewCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textViewnombre);
            textViewPrecio = itemView.findViewById(R.id.textViewprecio);
            //textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewTipo = itemView.findViewById(R.id.textViewTipo);
            textViewCantidad = itemView.findViewById(R.id.textViewcantidad);
        }
    }
}
