package com.leonardo.gerenciadordevendas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Produto;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.ArrayList;
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
            cv.put(DataBase.DATA_VENDA, venda.getDataVenda());
            cv.put(DataBase.IS_PARCELADO_VENDA, venda.isParcelado() ? 1 : 0);
            cv.put(DataBase.QUANTIDADE_PARCELAS_VENDA, venda.getQuantidadeParcelas());
            cv.put(DataBase.ID_CLIENTE_VENDA, venda.getIdCliente());

            long insertedId = conexao.insert(DataBase.TABELA_VENDA, "", cv);
            int idVenda = (int) (long) insertedId;

            venda.setId(idVenda);

        } finally {
            close();
        }
    }

    private String obterQueryBase() {
        return " SELECT * FROM " + DataBase.TABELA_VENDA + " venda " +
                //      " INNER JOIN " + DataBase.TABELA_PARCELA + " parcela " +
                //      " ON parcela." + DataBase.ID_VENDA_PARCELA + " = venda." + DataBase.ID_VENDA_PARCELA +
                " INNER JOIN " + DataBase.TABELA_CLIENTE + " cliente " +
                " ON cliente." + DataBase.ID_CLIENTE + "= venda." + DataBase.ID_CLIENTE_VENDA +
                " WHERE 1=1 ";
    }


    private String obterQueryBuscaData(String data) {

        return " SELECT * FROM " + DataBase.TABELA_VENDA + " venda " +
                //      " INNER JOIN " + DataBase.TABELA_PARCELA + " parcela " +
                //      " ON parcela." + DataBase.ID_VENDA_PARCELA + " = venda." + DataBase.ID_VENDA_PARCELA +
                " INNER JOIN " + DataBase.TABELA_CLIENTE + " cliente " +
                " ON cliente." + DataBase.ID_CLIENTE + "= venda." + DataBase.ID_CLIENTE_VENDA +
//                " INNER JOIN " + DataBase.TABELA_PRODUTO + " produto "
//                " ON produto." + DataBase.ID_PRODUTO + " = venda." + DataBase.ID_PRODUTO_VENDA
                " WHERE " + DataBase.DATA_VENDA + " LIKE '%" + data + "%'";
    }

    public List<Venda> findAll() {

        try {
            open();

            String query = obterQueryBase() + ";";

            Cursor cursor = conexao.rawQuery(query, null);
            List<Venda> vendas = new ArrayList<>();

            while (cursor.moveToNext()) {
                vendas.add(obterVenda(cursor));
            }

            return vendas;
        } finally {
            close();
        }
    }


    private Venda obterVenda(Cursor cursor) {
        Venda venda = new Venda();
        venda.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_VENDA)));
        venda.setIdCliente(cursor.getInt(cursor.getColumnIndex(DataBase.ID_CLIENTE_VENDA)));
        venda.setQuantidadeParcelas(cursor.getInt(cursor.getColumnIndex(DataBase.QUANTIDADE_PARCELAS_VENDA)));
        venda.setDataVenda(cursor.getString(cursor.getColumnIndex(DataBase.DATA_VENDA)));

        venda.setParcelado(venda.getQuantidadeParcelas() > 1);

/*        Produto produto = new Produto();
        produto.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PRODUTO)));
        produto.setTitulo(cursor.getString(cursor.getColumnIndex(DataBase.TITULO_PRODUTO)));
        produto.setDescricao(cursor.getString(cursor.getColumnIndex(DataBase.DESCRICAO_PRODUTO)));
        produto.setPreco(cursor.getDouble(cursor.getColumnIndex(DataBase.PRECO_PRODUTO)));
        produto.setIdCategoria(cursor.getColumnIndex(DataBase.ID_CATEGORIA_PRODUTO));*/

        //venda.setProdutoVenda(produto);

        Cliente cliente = new Cliente();
        cliente.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_CLIENTE)));
        cliente.setNome(cursor.getString(cursor.getColumnIndex(DataBase.NOME_CLIENTE)));
        cliente.setTelefone(cursor.getString(cursor.getColumnIndex(DataBase.TELEFONE_CLIENTE)));
        cliente.setRG(cursor.getString(cursor.getColumnIndex(DataBase.RG_CLIENTE)));
        cliente.setCPF(cursor.getString(cursor.getColumnIndex(DataBase.CPF_CLIENTE)));
        cliente.setEmail(cursor.getString(cursor.getColumnIndex(DataBase.EMAIL_CLIENTE)));

        venda.setClienteVenda(cliente);

        return venda;
    }

    public List<Venda> buscarPelaData(String data) {
        try {
            open();

            List<Venda> vendas = new ArrayList<>();
            String query = obterQueryBuscaData(data) + ";";

            Cursor cursor = conexao.rawQuery(query, null);

            while (cursor.moveToNext()) {
                vendas.add(obterVenda(cursor));
            }
            return vendas;
        } finally {
            close();
        }
    }
}
