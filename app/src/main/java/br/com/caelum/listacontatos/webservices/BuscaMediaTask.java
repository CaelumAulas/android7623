package br.com.caelum.listacontatos.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.listacontatos.converter.AlunoConverter;
import br.com.caelum.listacontatos.dao.AlunoDao;
import br.com.caelum.listacontatos.modelo.Aluno;

public class BuscaMediaTask extends AsyncTask<Void, Object, String> {


    private Context contexto;
    private ProgressDialog dialog;

    public BuscaMediaTask(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected void onPreExecute() {

        dialog = ProgressDialog.show(contexto, "Segura ai", "To carregando", false, false);
    }

    @Override
    public String doInBackground(Void... objects) {


        AlunoDao alunoDao = new AlunoDao(contexto);
        List<Aluno> alunos = alunoDao.getAlunos();
        alunoDao.close();

        AlunoConverter converter = new AlunoConverter();
        String json = converter.toJSON(alunos);
        Webclient webclient = new Webclient();

        String media = webclient.pegaMedia(json);


        return media;
    }

    @Override
    public void onPostExecute(String media) {
        dialog.dismiss();
        Toast.makeText(contexto, media, Toast.LENGTH_SHORT).show();
    }
}
