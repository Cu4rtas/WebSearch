package com.example.jham0.websearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Buscar extends AppCompatActivity {

    private ListView lvBusqueda;
    private EditText etBusqueda;
    private TextView tvSeconds, tvTimeRandomSearch;
    private Button btnBusqueda1, btnBusqueda2, btnBusqueda3;
    List<Web> busqueda;
    BinarySearchTree<String> randoms1 = Web.randomWebs(10000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        /**Componentes**/
        //TextView que muestra los segundos
        tvSeconds = findViewById(R.id.tvSeconds);
        tvTimeRandomSearch = findViewById(R.id.tvTimeRandomSearch);
        lvBusqueda = findViewById(R.id.lvBusqueda);
        etBusqueda = findViewById(R.id.editTextBuscar);
        tvSeconds.setText("Tiempo de Busqueda: ");
        tvTimeRandomSearch.setText("Tiempo Busqueda Aletatoria: ");
        //Botones de busqueda
        btnBusqueda1 = findViewById(R.id.btnBusqueda1);
        btnBusqueda2 = findViewById(R.id.btnBusqueda2);
        btnBusqueda3 = findViewById(R.id.btnBusqueda3);
        /**Metodos**/
        buscar();
        busquedasAleatorias();
    }

    private void busquedasAleatorias() {

        btnBusqueda1.setOnClickListener(v -> {
            Long time = System.currentTimeMillis();
            setTime(time,randoms1);
        });

        btnBusqueda2.setOnClickListener(v -> {
            Long time = System.currentTimeMillis();
            BinarySearchTree<String> randoms2 = Web.randomWebs(100000);
            setTime(time,randoms2);
        });

        btnBusqueda3.setOnClickListener(v -> {
            Long time = System.currentTimeMillis();
            BinarySearchTree<String> randoms3 = Web.randomWebs(1000000);
            setTime(time,randoms3);
        });
    }

    private void setTime(Long time, BinarySearchTree<String> randoms) {
        DecimalFormat df = new DecimalFormat("#.00000");
        if (findTime(etBusqueda.getText().toString(),randoms) < 0) {
            tvTimeRandomSearch.setText("Tiempo Busqueda Aleatoria: "+df.format((System.currentTimeMillis()-time)/1000)+"ms");
        } else {
            tvTimeRandomSearch.setText("Tiempo Busqueda Aleatoria: "+df.format(((findTime(etBusqueda.getText().toString().toUpperCase()
                    ,randoms)-time)/1000))+"ms");
        }
    }

    private Long findTime(String search, BinarySearchTree<String> tree) {
        if (tree.find(search)) return System.currentTimeMillis();
        else return -1L;
    }

    private void buscar() {
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
                DecimalFormat df = new DecimalFormat("#.00000");
                double newTime = new Long((System.currentTimeMillis() - timeA)/1000).doubleValue();
                tvSeconds.setText("Tiempo de Busqueda: " +(df.format(newTime))+"ms");
            }
        });
        onItemClick();
    }

    //TODO: Hacer que inserte web aleatorias, y que muestre los tiempos de busqueda

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
