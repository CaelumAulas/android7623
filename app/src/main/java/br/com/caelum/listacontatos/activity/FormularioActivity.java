package br.com.caelum.listacontatos.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.listacontatos.R;
import br.com.caelum.listacontatos.dao.AlunoDao;
import br.com.caelum.listacontatos.helper.FormularioHelper;
import br.com.caelum.listacontatos.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {
    private FormularioHelper helper;
    private String localArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        helper = new FormularioHelper(this);


        Intent intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null) {
            helper.populaCampos(aluno);
        }

        FloatingActionButton btnFoto = helper.getBtnFoto();

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                localArquivo = getExternalFilesDir("foto")
                        + "/" + System.currentTimeMillis() + ".jpg";

                File foto = new File(localArquivo);
                Uri local = Uri.fromFile(foto);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, local);

                startActivityForResult(irParaCamera, 123);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {

            if (resultCode == RESULT_OK) {

                helper.carregaFoto(localArquivo);
            } else {
                Toast.makeText(this, "Que pena que voce não tirou a foto", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_formulario, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_alunos_salvar) {

            if (helper.camposEstaoValidos()) {

                Aluno aluno = helper.pegaAlunoDoFormulario();

                AlunoDao dao = new AlunoDao(this);

                if (aluno.getId() == null) {
                    dao.insere(aluno);
                } else {
                    dao.altera(aluno);
                }
                dao.close();

                finish();
            } else {
                helper.mostraErro();
            }

        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
