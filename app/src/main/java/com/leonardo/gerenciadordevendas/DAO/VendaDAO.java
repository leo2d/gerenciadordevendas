package com.leonardo.gerenciadordevendas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

public class VendaDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;
    private Venda venda;

    public VendaDAO(Context context) {
        banco = new DataBase(context);
        this.context = context;
    }

    public void open() {
        conexao = banco.getWritableDatabase();
    }

    public void close() {
        conexao.close();
    }

    public void gravarVenda(Venda venda) {

        try {
            open();

            ContentValues cv = new ContentValues();


        } finally {
            close();
        }
    }

    public List<Venda> findAll() {
        return null;
    }
}
