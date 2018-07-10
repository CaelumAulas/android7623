package br.com.caelum.listacontatos.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.listacontatos.FormularioActivity;
import br.com.caelum.listacontatos.R;
import br.com.caelum.listacontatos.modelo.Aluno;

public class FormularioHelper {

    private Aluno aluno;

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText email;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {
        this.nome = activity.findViewById(R.id.formulario_nome);
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


}
