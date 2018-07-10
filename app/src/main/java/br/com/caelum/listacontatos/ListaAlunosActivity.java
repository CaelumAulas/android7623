package br.com.caelum.listacontatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.listacontatos.dao.AlunoDao;
import br.com.caelum.listacontatos.modelo.Aluno;

import static android.widget.AdapterView.OnItemLongClickListener;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        lista = findViewById(R.id.lista);

        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int posicao, long id) {


                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

                Toast.makeText(ListaAlunosActivity.this,
                        "Você clicou no " + aluno.getNome(),
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        lista.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,
                                           View view, int posicao, long id) {


                Toast.makeText(ListaAlunosActivity.this,
                        "Posicao " + posicao,
                        Toast.LENGTH_LONG)
                        .show();


                return true;
            }
        });

        FloatingActionButton btnAdd = findViewById(R.id.fab);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intencao = new Intent(ListaAlunosActivity.this,
                        FormularioActivity.class);
                startActivity(intencao);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        AlunoDao alunoDao = new AlunoDao(this);

        List<Aluno> alunos = alunoDao.getAlunos();

        alunoDao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);



        lista.setAdapter(adapter);

    }
}
