package com.example.foodease;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;


public class CarritoAdapter extends FirestoreRecyclerAdapter<comidas, CarritoAdapter.ViewHolder> {

    private final Context context;
    private final FirestoreHelper firestoreHelper;
    private int position;

    public CarritoAdapter(@NonNull FirestoreRecyclerOptions<comidas> options, Context context, FirestoreHelper firestoreHelper) {
        super(options);
        this.context = context;
        this.firestoreHelper = firestoreHelper;
    }

    public List<comidas> getCarritoList() {
        List<comidas> carritoList = new ArrayList<>();
        for (int i = 0; i < getItemCount(); i++) {
            carritoList.add(getItem(i));
        }
        return carritoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_carrito, parent, false);
        return new ViewHolder(view);
    }

    public void eliminarItem(int position) {
        getSnapshots().getSnapshot(position).getReference().update("eliminado", true);
    }

    public void limpiarCarrito() {
        //Elimina todos los elementos del conjunto de datos
        getSnapshots().clear();
        //Notifica al adaptador que los datos han cambiado
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewnombre;
        TextView textViewprecio;
        TextView textViewcantidad;
        Button masCantidad;
        Button menosCantidad;
        Button btnEnviarCarrito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //final int position = getAbsoluteAdapterPosition();
            //Inicia las visitas aquí
            textViewnombre = itemView.findViewById(R.id.textViewnombre);
            textViewprecio = itemView.findViewById(R.id.textViewprecio);
            textViewcantidad = itemView.findViewById(R.id.textViewcantidad);
            masCantidad = itemView.findViewById(R.id.masCantidad);
            menosCantidad = itemView.findViewById(R.id.menosCantidad);
            btnEnviarCarrito = itemView.findViewById(R.id.buttonEnviarCarrito);

            // Agregar clic largo para eliminar visualmente el producto
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        //Mostrar un cuadro de dialogo de confirmación
                        new AlertDialog.Builder(context)
                                .setTitle("Eliminar del carrito")
                                .setMessage("¿Estas seguro de que quieres eliminar del carrito?")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //Lamar al metodo eliminarItem aquí
                                                eliminarItem(position);
                                            }
                                        })
                                                .setNegativeButton(android.R.string.no, null)
                                                        .show();
                        return true; // Indica que el clic largo ha sido manejado
                    }
                    return false;
                }
            });

            //Agregar click al boton "Enviar Pedido"
            btnEnviarCarrito.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   //Obtener la lista de productos del carrito
                   List<comidas> carrito = getSnapshots();
                   //Guardar el pedido en Firestore usando la clase FirestoreHelper
                   firestoreHelper.guardarPedido(carrito);
                   //Mostrar un mensaje de éxito y limpiar el carrito
                   Toast.makeText(context, "Pedido enviado con éxito", Toast.LENGTH_SHORT).show();
                   limpiarCarrito();
               }
            });

            //Agregar lógica para incrementar la cantidad
            masCantidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Obtener la posición actual
                    int position = getBindingAdapterPosition();
                    //Incrementar la cantidad en el modelo
                    int nuevaCantidad = Math.max(0, getItem(position).getCantidad() - 1);

                    //Actualizar en Firestore si es necesario
                    getSnapshots().getSnapshot(position).getReference().update("cantidad", nuevaCantidad);

                    //Actualiza el modelo local
                    getItem(position).setCantidad(nuevaCantidad);
                }
            });

            //Agregar lógica para decrementar la cantidad
            menosCantidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Asegurarse de que la cantidad no sea negativa
                    int nuevaCantidad = Math.max(0, getItem(position).getCantidad() - 1);

                    // Actualizar en Firestore
                    getSnapshots().getSnapshot(position).getReference().update("cantidad", nuevaCantidad);

                    // Actualizar en el modelo local
                    getItem(position).setCantidad(nuevaCantidad);
                }
            });
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull comidas model) {
        //Configura la vistas del elemento del carrito con los datos de modelo
        if (!model.isEliminado()) {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.textViewnombre.setText(model.getNombreComida());
            holder.textViewprecio.setText(String.valueOf(model.getPrecio()));
            holder.textViewcantidad.setText(String.valueOf(model.getCantidad()));


        } else {
            //Si el elemnto esta marcado como eliminado, oculta la vista
            holder.itemView.setVisibility(View.GONE);
        }
    }

}
