package com.example.foodease;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreDB {
    private static final String PEDIDOS_COLLECTION = "pedidos";

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static CollectionReference getPedidosCollection() {
        return db.collection(PEDIDOS_COLLECTION);
    }
}
