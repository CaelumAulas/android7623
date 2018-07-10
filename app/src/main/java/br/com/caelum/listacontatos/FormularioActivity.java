package br.com.caelum.listacontatos;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.caelum.listacontatos.helper.FormularioHelper;
import br.com.caelum.listacontatos.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        helper = new FormularioHelper(this);

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

            Aluno aluno = helper.pegaAlunoDoFormulario();

            Toast.makeText(this, aluno.getNome(), Toast.LENGTH_SHORT).show();


        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
