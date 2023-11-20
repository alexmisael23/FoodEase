package com.example.foodease;

import com.google.firebase.firestore.CollectionReference;

import java.util.List;

public class FirestoreHelper {
    public void guardarPedido(List<comidas> carrito) {
        CollectionReference pedidosCollections = FirestoreDB.getPedidosCollection();

        for (comidas comida : carrito) {

        }
    }
}
