package com.projeto.jadieleudes.mysmallspace.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.Context;

import com.projeto.jadieleudes.mysmallspace.infra.DataBase;
import com.projeto.jadieleudes.mysmallspace.dominio.*;

/**
 * Método de persistência da classe Sessao
 * @see SessaoDAO
 */

public class SessaoDAO {
    private DataBase dbHelper;
    private SQLiteDatabase feelingsDb;
    private UsuarioDAO usuarioDAO;

    /**
     * Constructor
     * @param context
     */

    public SessaoDAO (Context context){
        dbHelper = new DataBase(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    /**
     * Método que recebe Sessao da Pessoa a logar e insere na TABELA_SESSAO do banco de dados
     * @param sessao
     */

    public void inserirIdUsuario(Sessao sessao){
        feelingsDb = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String colunaIdPessoa = DataBase.ID_USUARIO;
        values.put(colunaIdPessoa, sessao.getUsuarioLogado().getId());

        feelingsDb.insert(DataBase.TABELA_SESSAO, null, values);
    }

    /**
     * Método que na TABELA_SESSAO busca qual pessoa está logada
     * @return Retorna objeto Pessoa que está logada no sistema, se não, retorna null
     */

    public Usuario getUsuarioLogado(){
        feelingsDb = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DataBase.TABELA_SESSAO;

        Cursor cursor = feelingsDb.rawQuery(query, null);

        Usuario pessoa = null;

        if(cursor.moveToNext()){
            String colunaUsuarioId = DataBase.ID_USUARIO;
            int indexColunaUsuarioId = cursor.getColumnIndex(colunaUsuarioId);
            long idUsuario = cursor.getInt(indexColunaUsuarioId);
            pessoa = usuarioDAO.getUsuarioId(idUsuario);
        }
        cursor.close();
        feelingsDb.close();
        return pessoa;
    }

    /**
     * Encerra a Sessao da Pessoa no sistema
     */

    public void removerPessoa() {
        feelingsDb = dbHelper.getWritableDatabase();
        feelingsDb.delete(DataBase.TABELA_SESSAO, null, null);
        feelingsDb.close();
    }
}