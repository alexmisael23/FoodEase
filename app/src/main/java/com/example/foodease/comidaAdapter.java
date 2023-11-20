package com.example.foodease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class comidaAdapter extends FirestoreRecyclerAdapter<comidas, comidaAdapter.ViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public comidaAdapter(@NonNull FirestoreRecyclerOptions<comidas> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull comidas comidas) {
        holder.textViewNombre.setText(comidas.getNombreComida());
        holder.textViewPrecio.setText(String.valueOf(comidas.getPrecio()));
      //  holder.textViewFecha.setText(String.valueOf(comidas.getFechaCreacion()));
        holder.textViewTipo.setText(comidas.getTipoComida());
        holder.textViewCantidad.setText(String.valueOf(comidas.getCantidad()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onItemClick(getSnapshots().getSnapshot(adapterPosition), adapterPosition);
                }
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comidas, parent, false);
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
