package com.example.foodease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MostrarActivity extends AppCompatActivity {

    Button buttonVerCarrito;

    RecyclerView recyclerViewComidas;
    comidaAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        buttonVerCarrito = findViewById(R.id.buttonVerCarrito);
        recyclerViewComidas = findViewById(R.id.recyclermostrarcomidas);
        recyclerViewComidas.setLayoutManager(new LinearLayoutManager(this));
        mFirestore = FirebaseFirestore.getInstance();

        Query query = mFirestore.collection("comidas").whereEqualTo("tipoComida","Chilaquiles");

        FirestoreRecyclerOptions<comidas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<comidas>()
                .setQuery(query, comidas.class).build();

        mAdapter = new comidaAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        recyclerViewComidas.setAdapter(mAdapter);

        // Configura el OnItemClickListener y agrega productos al carrito
        // Configura el OnItemClickListener y agrega productos al carrito
        mAdapter.setOnItemClickListener(new comidaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                mAdapter.addToCart(position);
                Toast.makeText(MostrarActivity.this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show();
            }
        });

        // Agrega un botón o acción para ver el carrito
        buttonVerCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén los elementos del carrito
                List<comidas> carritoItems = mAdapter.getCarritoItems();

                // Puedes pasar la lista de elementos del carrito a CarritoActivity
                Intent intent = new Intent(MostrarActivity.this, CarritoActivity.class);
                intent.putExtra("carritoItems", (Serializable) carritoItems);
                startActivity(intent);
            }
        });

        // Establece el adaptador en el RecyclerView
        recyclerViewComidas.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    List<comidas> carritoItems =  new ArrayList<>();

}