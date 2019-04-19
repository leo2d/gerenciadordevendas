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
            categoria.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_CATEGORIA)));
            categoria.setNome(cursor.getString(cursor.getColumnIndex(DataBase.NOME_CATEGORIA)));
            categorias.add(categoria);
        }
        return categorias;
    }

    public Categoria findById(int id) {
        // faz o select para procurar todos pelo ID

        String selectUsuario = "SELECT * FROM " + DataBase.TABELA_CATEGORIA + " WHERE " + DataBase.ID_CATEGORIA + "==" + id + ";";
        Cursor cursor = conexao.rawQuery(selectUsuario, null);

        Categoria categoria = new Categoria();

        //Percorre cada atributo de usuario
        if (cursor.moveToNext()) {
            categoria.setNome(cursor.getString(cursor.getColumnIndex(DataBase.NOME_CATEGORIA)));
        }
        return categoria;
    }
}
