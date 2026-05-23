package com.example.animalesfragmentos;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    ListView listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAnimales = findViewById(R.id.listaAnimales);

        String[] animales = {
                "Gato",
                "Perro",
                "Vaca"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                animales
        );

        listaAnimales.setAdapter(adapter);

        cambiarFragmento(new GatoFragment());

        listaAnimales.setOnItemClickListener((parent, view, position, id) -> {

            if (position == 0) {
                cambiarFragmento(new GatoFragment());
            }

            else if (position == 1) {
                cambiarFragmento(new PerroFragment());
            }

            else if (position == 2) {
                cambiarFragmento(new VacaFragment());
            }
        });
    }

    private void cambiarFragmento(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragmentos, fragment)
                .commit();
    }
}