package com.leonardo.gerenciadordevendas.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context contexto;

    public CategoriaDAO(Context context) {
        banco = new DataBase(context);
        this.contexto = context;
    }

    public void open() {
        conexao = banco.getWritableDatabase();
    }

    public void close() {
        conexao.close();
    }

    public List<Categoria> findAll() {
        String selectCategoria = "SELECT * FROM " + DataBase.TABELA_CATEGORIA + " ;";

        Cursor cursor = conexao.rawQuery(selectCategoria, null);
        ArrayList<Categoria> categorias = new ArrayList<>();

        while (cursor.moveToNext()) {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            categorias.add(categoria);
        }
        return categorias;
    }
}
