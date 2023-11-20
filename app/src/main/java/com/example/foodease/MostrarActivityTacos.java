package com.example.foodease;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MostrarActivityTacos extends AppCompatActivity {

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

        Query query = mFirestore.collection("comidas").whereEqualTo("tipoComida","Tacos");

        FirestoreRecyclerOptions<comidas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<comidas>()
                .setQuery(query, comidas.class).build();

        mAdapter = new comidaAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
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
}
