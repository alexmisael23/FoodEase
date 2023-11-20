package com.example.foodease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;


public class CarritoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CarritoAdapter carritoAdapter;
    private Button btnEnviarCarrito;

    private FirebaseFirestore mFirestore;
    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        //Inicializa firebasehelper
        firestoreHelper = new FirestoreHelper();

        //Botones de menu
        ImageButton ImagenButtonComida = findViewById(R.id.ImagenButtonCom);
        ImageButton imageButtonMenu = findViewById(R.id.imageButtonMen);
        ImageButton imageButtonMesas = findViewById(R.id.imageButtonMes);
        ImageButton imageButtonBebidas = findViewById(R.id.imageButtonBeb);
        ImageButton imageButtonS = findViewById(R.id.imageButtonS);

        recyclerView = findViewById(R.id.RecyclerViewCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Inicializar el boton Enviar Carrito
        btnEnviarCarrito = findViewById(R.id.buttonEnviarCarrito);

        mFirestore = FirebaseFirestore.getInstance();
        Query query = mFirestore.collection("comidas");

        FirestoreRecyclerOptions<comidas> options = new FirestoreRecyclerOptions.Builder<comidas>()
                .setQuery(query, comidas.class)
                .build();

        carritoAdapter = new CarritoAdapter(options, this, new FirestoreHelper());
        recyclerView.setAdapter(carritoAdapter);

        // Configurar ItemTouchHelper
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // No es necesario implementar aquí, ya que el clic largo maneja la eliminación
                int position = viewHolder.getAdapterPosition();
                carritoAdapter.eliminarItem(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        //Redirecciones
        imageButtonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para abrir la segunda actividad
                Intent intent = new Intent(CarritoActivity.this, SalonActivity.class);
                startActivity(intent); // Inicia la segunda actividad
            }
        });
        ImagenButtonComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para abrir la segunda actividad
                Intent intent = new Intent(CarritoActivity.this, ComidasActivity.class);
                startActivity(intent); // Inicia la segunda actividad
            }
        });
        imageButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para abrir la segunda actividad
                Intent intent = new Intent(CarritoActivity.this, MenuActivity.class);
                startActivity(intent); // Inicia la segunda actividad
            }
        });
        imageButtonBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para abrir la segunda actividad
                Intent intent = new Intent(CarritoActivity.this, BebidasActivity.class);
                startActivity(intent); // Inicia la segunda actividad
            }
        });
        imageButtonMesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para abrir la segunda actividad
                Intent intent = new Intent(CarritoActivity.this, MesasActivity.class);
                startActivity(intent); // Inicia la segunda actividad
            }
        });

        btnEnviarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obten la lista de productos en el carrito
                List<comidas> carrito = carritoAdapter.getCarritoList();

                //Verifica si el carrito esta vacio
                if (carrito.isEmpty()) {
                    firestoreHelper.guardarPedido(carrito);
                    Toast.makeText(CarritoActivity.this, "Pedido enviado con exito", Toast.LENGTH_SHORT).show();
                }else {
                    //Mostrar un mensaje indicado que el carrito está vacio
                    Toast.makeText(CarritoActivity.this, "El carrito esta vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Método para abrir una actividad
    private void abrirActividad(Class<?> actividad) {
        Intent intent = new Intent(CarritoActivity.this, actividad);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        carritoAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        carritoAdapter.stopListening();
    }
}
