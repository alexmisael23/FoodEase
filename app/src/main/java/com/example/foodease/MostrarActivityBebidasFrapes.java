package com.example.foodease;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MostrarActivityBebidasFrapes extends AppCompatActivity {

    RecyclerView recyclerViewBebidas;
    bebidaAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_bebidas);

        recyclerViewBebidas = findViewById(R.id.recyclermostrarBebidas);
        recyclerViewBebidas.setLayoutManager(new LinearLayoutManager(this));
        mFirestore = FirebaseFirestore.getInstance();

        Query query = mFirestore.collection("bebidas").whereEqualTo("tipoBebida","Frapes");

        FirestoreRecyclerOptions<bebidas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<bebidas>()
                .setQuery(query, bebidas.class).build();

        mAdapter = new bebidaAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        recyclerViewBebidas.setAdapter(mAdapter);
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
