package com.example.jham0.websearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import org.w3c.dom.DOMStringList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Insertar extends AppCompatActivity {

    /**Components**/
    private EditText etInsertarURL, etInsertarKeywords;
    private Button btnInsercion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        connect();
        InsertarOModificar();
        obtenerDatos();
    }

    private void connect() {
        etInsertarURL = findViewById(R.id.etInsertarUrl);
        etInsertarKeywords = findViewById(R.id.etInsertarKeywords);
        btnInsercion = findViewById(R.id.btnInsercion);
    }

    private void InsertarOModificar() {
        btnInsercion.setOnClickListener(view -> {
            if (etInsertarURL.getText().toString().isEmpty() || etInsertarKeywords.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Campo vacío", Toast.LENGTH_SHORT).show();
            } else {
                Web newWeb = new Web(etInsertarURL.getText().toString().toLowerCase());
                String[] keywords = etInsertarKeywords.getText().toString().toLowerCase().split(",");
                newWeb.addKeyWords(keywords);
                if(AppMain.isModifyingWeb()){
                    modificar(newWeb);
                } else {
                    insertar(newWeb);
                }
                Toast.makeText(getApplicationContext(), "Web insertada", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        });
    }

    private void insertar(Web newWeb){
        AppMain.insert(newWeb);
    }

    private void modificar(Web newWeb){
        AppMain.modificar(newWeb);
    }
    /**
     * Obtiene los datos pasados por Bundle del
     * activity Buscar(lvBusqueda)
     */
    private void obtenerDatos() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            etInsertarURL.setText(bundle.getString("Url"));
            etInsertarKeywords.setText(bundle.getString("Keywords"));
        }
    }

    private void clearFields() {
        etInsertarURL.setText("");
        etInsertarKeywords.setText("");
    }


}
