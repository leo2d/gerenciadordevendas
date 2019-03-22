package com.leonardo.gerenciadordevendas.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Categoria;
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
                + produto.getProdutoCategoria().getIdCategoria() + "');";

        conexao.execSQL(insertCorrida);

    }

    public List<Produto> findByCategoryProductId(int id) {

        String selectProductCategoryID = "SELECT * FROM " + DataBase.TABELA_PRODUTO + " WHERE "
                + DataBase.ID_CATEGORIA_PRODUTO + "==" + id + ";";

        Cursor cursor = conexao.rawQuery(selectProductCategoryID, null);
        ArrayList<Produto> produtos = new ArrayList<>();

        while (cursor.moveToNext()) {

            produto = new Produto();
            produto.setTitulo(cursor.getString(cursor.getColumnIndex(DataBase.TITULO_PRODUTO)));
            produto.setDescricao(cursor.getString(cursor.getColumnIndex(DataBase.DESCRICAO_PRODUTO)));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex(DataBase.PRECO_PRODUTO)));
            //PARA PEGAR O ID
            int idCategoria = cursor.getColumnIndex(DataBase.ID_CATEGORIA_PRODUTO);
            CategoriaDAO categoriaDAO = new CategoriaDAO(context);
            categoriaDAO.open();

            produto.setProdutoCategoria(categoriaDAO.findById(idCategoria));
            categoriaDAO.close();

            produtos.add(produto);
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

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndex(DataBase.ID_CATEGORIA_PRODUTO)));

                Produto produto = new Produto();
                produto.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PRODUTO)));
                produto.setTitulo(cursor.getString(cursor.getColumnIndex(DataBase.TITULO_PRODUTO)));
                produto.setDescricao(cursor.getString(cursor.getColumnIndex(DataBase.DESCRICAO_PRODUTO)));
                produto.setPreco(cursor.getDouble(cursor.getColumnIndex(DataBase.PRECO_PRODUTO)));
                produto.setProdutoCategoria(categoria);
                produtos.add(produto);
            }

            return produtos;

        } finally {
            close();
        }
    }

}
