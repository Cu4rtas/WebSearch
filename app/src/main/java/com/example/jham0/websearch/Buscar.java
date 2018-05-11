package com.example.jham0.websearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Buscar extends AppCompatActivity {

    private ListView lvBusqueda;
    private EditText etBusqueda;
    private TextView tvSeconds;
    List<Web> busqueda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        /**Textview que muestra los segundos**/
        tvSeconds = findViewById(R.id.tvSeconds);
        lvBusqueda = findViewById(R.id.lvBusqueda);
        etBusqueda = findViewById(R.id.editTextBuscar);
        tvSeconds.setText("Tiempo de Busqueda: ");
        etBusqueda.addTextChangedListener(new TextWatcher() {
            Long timeA;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lvBusqueda.setAdapter(null);
                timeA = System.currentTimeMillis();
                busqueda = AppMain.getAllWebsWithWords(etBusqueda.getText().toString().split(","));
                lvBusqueda.setAdapter(new ArrayAdapter<Web>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        busqueda));
            }

            @Override
            public void afterTextChanged(Editable s) {
                /**Calcula el valor en milisegundos del tiempo de busqueda**/
                double newTime = new Long((System.currentTimeMillis() - timeA)/1000).doubleValue();
                tvSeconds.setText("Tiempo de Busqueda: " +(newTime)+"ms");
            }
        });

        onItemClick();
    }

    /**
     * Se encarga de obtener la informacion del item seleccionado
     * y enviarla al activity Insert para actualizar los datos.
     */
    private void onItemClick() {
        lvBusqueda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Insertar.class);
                intent.putExtra("Url", busqueda.get(position)
                                .getCuerpoURL());
                intent.putExtra("Keywords", busqueda.get(position)
                                .getPalabrasClaveConComa());
                AppMain.setWebAModificar(AppMain.getWebs().getAllItems().get(position));
                startActivity(intent);
                finish();
            }
        });
    }
}
