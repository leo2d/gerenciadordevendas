package com.leonardo.gerenciadordevendas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.ItemVenda;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.ArrayList;
import java.util.List;

public class ItemVendaDAO {

    private SQLiteDatabase conexao;
    private DataBase dataBase;
    private Context context;

    public ItemVendaDAO(Context context) {
        dataBase = new DataBase(context);
        this.context = context;
    }

    public void open() {
        conexao = dataBase.getWritableDatabase();
    }

    public void close() {
        conexao.close();
    }

    private String obterQuerybase() {
        return "SELECT * FROM " + DataBase.TABELA_ITEM_VENDA + " item "
                + "INNER JOIN " + DataBase.TABELA_VENDA + " venda "
                + "ON venda." + DataBase.ID_VENDA + " = item." + DataBase.ID_VENDA_ITEM
                + "INNER JOIN " + DataBase.TABELA_PRODUTO + " produto "
                + "ON produto." + DataBase.ID_PRODUTO + " = item." + DataBase.ID_PRODUTO_VENDA
                + " WHERE 1=1 ";
    }

    public void gravarItensVenda(List<ItemVenda> itensVenda) {
        for (ItemVenda item : itensVenda) {
            gravarItemVenda(item);
        }
    }

    public void gravarItemVenda(ItemVenda itemVenda) {

        try {
            open();

            ContentValues cv = new ContentValues();
            cv.put(DataBase.ID_VENDA_ITEM, itemVenda.getIdVenda());
            cv.put(DataBase.ID_PRODUTO_VENDA, itemVenda.getIdProduto());
            cv.put(DataBase.QUANTIDADE, itemVenda.getQuantidade());

            long insertedId = conexao.insert(DataBase.TABELA_ITEM_VENDA, "", cv);
            int idItemVenda = (int) (long) insertedId;

            itemVenda.setId(idItemVenda);

        } finally {
            close();
        }
    }

    public List<ItemVenda> buscarPorVenda(int idVenda) {

        try {
            open();

            String query = obterQuerybase();
            query += " AND item." + DataBase.ID_VENDA_ITEM + " = " + idVenda;
            query += " ;";

            Cursor cursor = conexao.rawQuery(query, null);
            List<ItemVenda> itens = new ArrayList<>();

            while (cursor.moveToNext()) {
                itens.add(obterItemVenda(cursor));
            }

            return itens;

        } finally {
            close();
        }
    }

    private ItemVenda obterItemVenda(Cursor cursor) {
        ItemVenda itemVenda = new ItemVenda();

        itemVenda.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_ITEM_VENDA)));
        itemVenda.setQuantidade(cursor.getInt(cursor.getColumnIndex(DataBase.FOI_PAGA_PARCELA)));
        itemVenda.setIdProduto(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PRODUTO_VENDA)));
        itemVenda.setIdVenda(cursor.getInt(cursor.getColumnIndex(DataBase.ID_VENDA_ITEM)));

        Produto produto = new Produto();
        produto.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PRODUTO)));
        produto.setTitulo(cursor.getString(cursor.getColumnIndex(DataBase.TITULO_PRODUTO)));
        produto.setDescricao(cursor.getString(cursor.getColumnIndex(DataBase.DESCRICAO_PRODUTO)));
        produto.setPreco(cursor.getDouble(cursor.getColumnIndex(DataBase.PRECO_PRODUTO)));
        produto.setIdCategoria(cursor.getColumnIndex(DataBase.ID_CATEGORIA_PRODUTO));

        itemVenda.setProduto(produto);

        return itemVenda;
    }

}
