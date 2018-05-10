package com.example.jham0.websearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Buscar extends AppCompatActivity {

    ListView lvBusqueda;
    EditText etBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        lvBusqueda = findViewById(R.id.lvBusqueda);
        etBusqueda = findViewById(R.id.editTextBuscar);

        etBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lvBusqueda.setAdapter(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                lvBusqueda.setAdapter(new ArrayAdapter<Web>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        AppMain.getWebsByPalabrasClave(etBusqueda.getText().toString().split(","))));
            }
        });
    }
}
