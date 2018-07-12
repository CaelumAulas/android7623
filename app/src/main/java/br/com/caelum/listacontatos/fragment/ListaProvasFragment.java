package br.com.caelum.listacontatos.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.listacontatos.R;
import br.com.caelum.listacontatos.modelo.Prova;

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);


        ListView lista = view.findViewById(R.id.fragment_lista_provas);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Prova prova = (Prova) adapterView.getItemAtPosition(posicao);

                Toast.makeText(getContext(), prova.getMateria(), Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<Prova> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listaProvas());
        lista.setAdapter(adapter);

        return view;

    }

    @NonNull
    private List<Prova> listaProvas() {
        Prova matematica = new Prova("Matematica", "10/10/2010");
        Prova portugues = new Prova("Portugues", "11/10/2010");
        Prova historia = new Prova("Historia", "12/10/2010");


        matematica.setTopicos(Arrays.asList("Algebra", "Soma", "Multiplicação"));
        portugues.setTopicos(Arrays.asList("Concordancia", "Sufixo", "Prefixo"));
        historia.setTopicos(Arrays.asList("Brasil", "Roma antiga", "Mitologia Grega"));


        return Arrays.asList(matematica, portugues, historia);
    }
}
