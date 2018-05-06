package com.example.jham0.websearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.util.TreeSet;


public class Inicio extends AppCompatActivity {
    /**Components**/
    EditText etSearch;
    Button btnInsertar,btnIrBuscar;
    /**Atributos**/
    BinarySearchTree<Web> webs = new BinarySearchTree<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        connect();
        insertar();
        buscar();
    }

    private void connect() {
        btnInsertar = findViewById(R.id.btnInsertar);
        btnIrBuscar = findViewById(R.id.btnIrBuscar);
    }

    private void insertar() {
        btnInsertar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Insertar.class);
            startActivity(intent);
        });
    }

    private void buscar() {
        btnIrBuscar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Buscar.class);
            startActivity(intent);
        });
    }
}
