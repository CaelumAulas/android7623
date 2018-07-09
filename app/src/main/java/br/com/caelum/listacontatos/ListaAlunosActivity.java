package br.com.caelum.listacontatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);


        String[] alunos = {"Augusto", "Daniel", "Fernando", "Gabriel",
                "José ", "Nicolas", "Sandro", "Thaize"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);


        ListView lista = findViewById(R.id.lista);


        lista.setAdapter(adapter);

    }
}
