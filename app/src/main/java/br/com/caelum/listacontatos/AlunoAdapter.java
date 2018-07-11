package br.com.caelum.listacontatos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.listacontatos.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter {


    private List<Aluno> alunos;
    private Activity context;


    public AlunoAdapter(List<Aluno> alunos, Activity context) {
        this.alunos = alunos;
        this.context = context;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View item = inflater.inflate(R.layout.aluno_item, viewGroup, false);


        Aluno aluno = alunos.get(position);

        TextView nome = item.findViewById(R.id.item_aluno_nome);
        TextView telefone = item.findViewById(R.id.item_aluno_telefone);
        ImageView foto = item.findViewById(R.id.item_aluno_foto);

        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());


        if (aluno.getCaminhoFoto() != null) {

            Bitmap bitmap = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 60, 60, true);

            foto.setImageBitmap(bitmapReduzido);
        }

        return item;
    }
}
