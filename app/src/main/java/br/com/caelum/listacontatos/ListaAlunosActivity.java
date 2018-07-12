package br.com.caelum.listacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.listacontatos.dao.AlunoDao;
import br.com.caelum.listacontatos.modelo.Aluno;
import br.com.caelum.listacontatos.webservices.BuscaMediaTask;

import static android.content.DialogInterface.OnClickListener;
import static android.view.MenuItem.OnMenuItemClickListener;

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


                Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);

                Intent edicao = new Intent(ListaAlunosActivity.this,
                        FormularioActivity.class);


                edicao.putExtra("aluno", aluno);

                startActivity(edicao);




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


        registerForContextMenu(lista);

    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();

    }

    private void carregaLista() {
        AlunoDao alunoDao = new AlunoDao(this);

        List<Aluno> alunos = alunoDao.getAlunos();

        alunoDao.close();

        AlunoAdapter adapter = new AlunoAdapter(alunos, this);


        lista.setAdapter(adapter);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenuInfo menuInfo) {


        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        int posicao = info.position;
        final Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

        MenuItem excluir = menu.add("Excluir");
        MenuItem sms = menu.add("SMS");
        MenuItem ligar = menu.add("Ligar");
        MenuItem site = menu.add("Site");
        MenuItem mapa = menu.add("Mapa");

        excluir.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setMessage("Você quer deletar esse aluno ? ")
                        .setPositiveButton("Sim", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlunoDao alunoDao = new AlunoDao(ListaAlunosActivity.this);

                                alunoDao.excluir(aluno);

                                alunoDao.close();

                                carregaLista();

                            }
                        }).setNegativeButton("Não", null)
                        .show();


                return false;
            }
        });


        sms.setIntent(mandarSmsPara(aluno));

        mapa.setIntent(vaiParaEnderecoDo(aluno));


    }

    @NonNull
    private Intent vaiParaEnderecoDo(Aluno aluno) {
        Intent irParaMapa = new Intent(Intent.ACTION_VIEW);

        irParaMapa.setData(Uri.parse("geo:0,0?z=15&q=" + aluno.getEndereco()));
        return irParaMapa;
    }

    @NonNull
    private Intent mandarSmsPara(Aluno aluno) {
        Intent irSms = new Intent(Intent.ACTION_VIEW);

        irSms.setData(Uri.parse("sms:" + aluno.getTelefone()));

        irSms.putExtra("sms_body", "Sua nota foi : " + aluno.getNota());
        return irSms;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);


        if (requestCode == 123) {
            int resultado = grantResults[0];

            if (resultado == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Preciso da permissão para funcionnar", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_lista_alunos, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_lista_alunos_media:


                BuscaMediaTask task = new BuscaMediaTask(this);

                task.execute();


                return true;


        }


        return true;
    }


}
