package com.projeto.jadieleudes.mysmallspace.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper; // Cria banco de dados

/**
 * Classe responsável por criar tabelas e o banco de dados
 */

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mysmallspace.db";

    // TABELA USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String ID = "_id";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_NICK = "nick";
    public static final String USUARIO_NOME = "nome";

    //TABELA DE SESSAO DO USUARIO
    // A Pessoa que estiver nessa tabela está logada, quando deslogar tirar da tabela
    // Essa tabela só tem o ID da pessoa logada
    public static final String TABELA_SESSAO = "sessao";
    public static final String ID_USUARIO = "usuario_id";

    private Context context;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_USUARIO + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USUARIO_EMAIL + " TEXT NOT NULL, " +
                USUARIO_SENHA + " TEXT NOT NULL, " +
                USUARIO_NICK + " TEXT NOT NULL, " +
                USUARIO_NOME + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABELA_SESSAO + " (" +
                ID_USUARIO + " INTEGER);");
    }

    //Atualização da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String query1 = "DROP TABLE IF EXISTS " + TABELA_USUARIO;
        db.execSQL(query1);

        String query2 = "DROP TABLE IF EXISTS " + TABELA_SESSAO;
        db.execSQL(query2);

        this.onCreate(db);
    }
}