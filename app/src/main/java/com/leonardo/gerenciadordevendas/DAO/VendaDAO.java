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
            cv.put(DataBase.DATA_VENDA, venda.getDataVenda());
            cv.put(DataBase.ID_PRODUTO_VENDA, venda.getIdProduto());
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
        return "SELECT * FROM " + DataBase.TABELA_VENDA + " venda " +
                "INNER JOIN " + DataBase.TABELA_PARCELA + " parcela " +
                "ON parcela." + DataBase.ID_VENDA_PARCELA + " = venda." + DataBase.ID_VENDA_PARCELA +
                "INNER JOIN " + DataBase.TABELA_CLIENTE + "cliente " +
                "ON cliente." + DataBase.ID_CLIENTE + "= venda." + DataBase.ID_CLIENTE_VENDA +
                "INNER JOIN " + DataBase.TABELA_PRODUTO + "produto" +
                "ON produto." + DataBase.ID_PRODUTO + " = venda." + DataBase.ID_PRODUTO_VENDA
                + "WHERE 1=1";
    }

    public List<Venda> findAll() {

        try {
            open();
            String query = obterQueryBase() + ";";
            return null;
        } finally {
            close();
        }
    }
}
