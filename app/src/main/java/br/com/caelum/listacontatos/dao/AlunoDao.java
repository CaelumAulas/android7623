package br.com.caelum.listacontatos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

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

        ContentValues dados = getContentValues(aluno);

        database.insert("Alunos", null, dados);


    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();

        dados.put("nome", aluno.getNome());
        dados.put("telefone", aluno.getTelefone());
        dados.put("endereco", aluno.getEndereco());
        dados.put("site", aluno.getEmail());
        dados.put("nota", aluno.getNota());
        return dados;
    }


    public void altera(Aluno aluno) {
        SQLiteDatabase database = getWritableDatabase();

        String[] condicao = {aluno.getId().toString()};

        ContentValues dados = getContentValues(aluno);

        database.update("Alunos", dados, "id=?", condicao);
    }


    public List<Aluno> getAlunos() {

        String sql = "select * from Alunos";

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        cursor.close();

        return alunos;
    }

    public void excluir(Aluno aluno) {

        SQLiteDatabase database = getWritableDatabase();

        String[] id = {aluno.getId().toString()};

        database.delete("Alunos", "id=?", id);


    }
}
