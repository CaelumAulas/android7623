package br.com.caelum.listacontatos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.caelum.listacontatos.modelo.Aluno;

public class AlunoDao extends SQLiteOpenHelper {

    private static final int VERSAO = 1;

    public AlunoDao(Context contexto) {
        super(contexto, "CadastroCaelum", null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table Alunos (" +
                "id integer primary key," +
                "nome text not null," +
                "telefone text," +
                "endereco text," +
                "site text," +
                "nota real" +
                ");";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int antiga, int nova) {
        // faz nada por enquanto
    }


    public void insere(Aluno aluno) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome", aluno.getNome());
        dados.put("telefone", aluno.getTelefone());
        dados.put("endereco", aluno.getEndereco());
        dados.put("site", aluno.getEmail());
        dados.put("nota", aluno.getNota());

        database.insert("Alunos", null, dados);


    }
}
