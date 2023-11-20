package com.example.foodease;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class MostrarActivity extends AppCompatActivity {

    RecyclerView recyclerViewComidas;
    comidaAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

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
        mAdapter.setOnItemClickListener(new comidaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                comidas comidasSeleccionada = documentSnapshot.toObject(comidas.class);
                if (comidasSeleccionada != null) {
                    //carritoItems.add(comidasSeleccionada);
                    Toast.makeText(MostrarActivity.this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show();
                }
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