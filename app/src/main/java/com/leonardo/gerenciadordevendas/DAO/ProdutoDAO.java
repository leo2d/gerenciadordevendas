package com.leonardo.gerenciadordevendas.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;
    private Produto produto;

    public ProdutoDAO(Context context) {
        banco = new DataBase(context);
        this.context = context;
    }

    public void open() {
        conexao = banco.getWritableDatabase();
    }

    public void close() {
        conexao.close();
    }

    public void gravarProduto(Produto produto) {

        String insertCorrida = "insert into " + DataBase.TABELA_PRODUTO + " ( " + DataBase.TITULO_PRODUTO + ", " +
                "" + DataBase.DESCRICAO_PRODUTO + ", " + DataBase.PRECO_PRODUTO + ", " + DataBase.ID_CATEGORIA_PRODUTO + ")"
                + " values ('" + produto.getTitulo() + "', '" + produto.getDescricao() + "', '" + produto.getPreco() + "', '"
                + produto.getIdCategoria() + "');";

        conexao.execSQL(insertCorrida);

    }

    private Produto obterProduto(Cursor cursor){

        Produto produto = new Produto();
        produto.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PRODUTO)));
        produto.setTitulo(cursor.getString(cursor.getColumnIndex(DataBase.TITULO_PRODUTO)));
        produto.setDescricao(cursor.getString(cursor.getColumnIndex(DataBase.DESCRICAO_PRODUTO)));
        produto.setPreco(cursor.getDouble(cursor.getColumnIndex(DataBase.PRECO_PRODUTO)));
        produto.setIdCategoria(cursor.getColumnIndex(DataBase.ID_CATEGORIA_PRODUTO));

        return produto;
    }

    public List<Produto> buscarPorCategoria(int id) {

        String query = "SELECT * FROM "
                + DataBase.TABELA_PRODUTO
                + " WHERE "
                + DataBase.ID_CATEGORIA_PRODUTO + "=" + id + ";";

        Cursor cursor = conexao.rawQuery(query, null);
        ArrayList<Produto> produtos = new ArrayList<>();

        while (cursor.moveToNext()) {
            produtos.add(obterProduto(cursor));
        }

        return produtos;
    }

    public List<Produto> findAll() {

        try {
            open();
            //IMPORTANTE
            String sql = "SELECT p. * FROM " + DataBase.TABELA_PRODUTO + " p " +
                    " JOIN " + DataBase.TABELA_CATEGORIA + " c " +
                    " ON ( c." + DataBase.ID_CATEGORIA + "=p." + DataBase.ID_CATEGORIA_PRODUTO + " )";

            Cursor cursor = conexao.rawQuery(sql, null);
            ArrayList<Produto> produtos = new ArrayList<>();

            while (cursor.moveToNext()) {

                Produto produto = new Produto();
                produto.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PRODUTO)));
                produto.setTitulo(cursor.getString(cursor.getColumnIndex(DataBase.TITULO_PRODUTO)));
                produto.setDescricao(cursor.getString(cursor.getColumnIndex(DataBase.DESCRICAO_PRODUTO)));
                produto.setPreco(cursor.getDouble(cursor.getColumnIndex(DataBase.PRECO_PRODUTO)));
                produto.setIdCategoria(cursor.getColumnIndex(DataBase.ID_CATEGORIA_PRODUTO));

                produtos.add(produto);
            }

            return produtos;

        } finally {
            close();
        }
    }

}
