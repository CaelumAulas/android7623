package br.com.caelum.listacontatos.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.listacontatos.R;
import br.com.caelum.listacontatos.fragment.DetalhesProvaFragment;
import br.com.caelum.listacontatos.fragment.ListaProvasFragment;
import br.com.caelum.listacontatos.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        if (taDeitado()) {
            transaction.replace(R.id.provas_frame_esquerda, new ListaProvasFragment());
            transaction.replace(R.id.provas_frame_direita, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_frame, new ListaProvasFragment());

        }
        transaction.commit();

    }

    private boolean taDeitado() {
        return getResources()
                .getBoolean(R.bool.isLand);
    }

    public void lidaComProva(Prova prova) {

        FragmentManager manager = this.getSupportFragmentManager();

        if (this.taDeitado()) {

            DetalhesProvaFragment detalhes = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_frame_direita);

            detalhes.populaCampos(prova);

        } else {

            FragmentTransaction transacao = manager.beginTransaction();

            DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();

            Bundle argumentos = new Bundle();

            argumentos.putSerializable("prova", prova);

            detalhesProvaFragment.setArguments(argumentos);

            transacao.replace(R.id.provas_frame, detalhesProvaFragment);
            transacao.addToBackStack(null);

            transacao.commit();

        }
    }
}
