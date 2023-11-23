package com.example.foodease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {
    RecyclerView recyclerViewCarrito;
    private Button btnEnviarCarrito;
    private CarritoAdapter carritoAdapter;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Botones de menú
        recyclerViewCarrito = findViewById(R.id.RecyclerViewCarrito);
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this));

        ImageButton ImagenButtonComida = findViewById(R.id.ImagenButtonCom);
        ImageButton imageButtonMenu = findViewById(R.id.imageButtonMen);
        ImageButton imageButtonMesas = findViewById(R.id.imageButtonMes);
        ImageButton imageButtonBebidas = findViewById(R.id.imageButtonBeb);
        ImageButton imageButtonS = findViewById(R.id.imageButtonS);

        // Obtén la lista de elementos del carrito desde el intent
        List<comidas> carritoItems = (List<comidas>) getIntent().getSerializableExtra("carritoItems");

        // Crea una instancia de CarritoAdapter proporcionando la lista y un OnItemClickListener
        carritoAdapter = new CarritoAdapter(carritoItems, new CarritoAdapter.OnItemClickListener() {
            @Override
            public void onDeleteItemClick(int position) {
                // Elimina el elemento del carrito cuando se hace clic en el botón de eliminación
                carritoItems.remove(position);
                carritoAdapter.notifyItemRemoved(position);
                carritoAdapter.notifyItemRangeChanged(position, carritoItems.size());
            }
        });

        // Configura el RecyclerView con el adaptador
        recyclerViewCarrito.setAdapter(carritoAdapter);

        // Redirecciones
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

        // Inicializa el botón para enviar el carrito
        btnEnviarCarrito = findViewById(R.id.btnEnviarCarrito);
        btnEnviarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarCarritoAlFirestore(carritoItems);
            }
        });
    }

    private void enviarCarritoAlFirestore(List<comidas> carritoItems) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Obtiene el ID del usuario autenticado
            String userId = user.getUid();

            // Crea una nueva colección en Firestore para los carritos
            // Puedes cambiar "carritos" por el nombre que desees
            CollectionReference carritosCollection = mFirestore.collection("carrito");

            // Crea un nuevo documento para el carrito del usuario
            DocumentReference carritoDocument = carritosCollection.document(userId);

            // Guarda los elementos del carrito en el documento
            carritoDocument.set(new HashMap<String, Object>() {{
                        put("carritoItems", carritoItems);
                    }})
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Éxito al guardar en Firestore
                            Toast.makeText(CarritoActivity.this, "Carrito enviado exitosamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error al guardar en Firestore
                            Toast.makeText(CarritoActivity.this, "Error al enviar el carrito", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // El usuario no está autenticado, debes manejar esta situación según tus necesidades
            Toast.makeText(CarritoActivity.this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }
}
