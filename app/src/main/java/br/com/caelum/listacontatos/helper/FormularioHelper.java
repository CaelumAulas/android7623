package br.com.caelum.listacontatos.helper;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.listacontatos.FormularioActivity;
import br.com.caelum.listacontatos.R;
import br.com.caelum.listacontatos.modelo.Aluno;

public class FormularioHelper {

    private Aluno aluno;

    private EditText nome;
    private TextInputLayout tilNome;
    private EditText telefone;
    private EditText endereco;
    private EditText email;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {
        this.nome = activity.findViewById(R.id.formulario_nome);
        this.tilNome = activity.findViewById(R.id.formulario_nome_til);
        this.telefone = activity.findViewById(R.id.formulario_telefone);
        this.endereco = activity.findViewById(R.id.formulario_endereco);
        this.email = activity.findViewById(R.id.formulario_email);
        this.nota = activity.findViewById(R.id.formulario_nota);

        this.aluno = new Aluno();
    }


    public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(nome.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(Double.valueOf(nota.getRating()));

        return aluno;
    }


    public boolean camposEstaoValidos() {

        return !nome.getText().toString().trim().isEmpty();
    }

    public void mostraErro() {
        tilNome.setError("Precisa ser preenchido");
    }

    public void populaCampos(Aluno aluno) {
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        email.setText(aluno.getEmail());

        nota.setRating(aluno.getNota().floatValue());

        this.aluno = aluno;
    }
}

