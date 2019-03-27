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
            cv.put(DataBase.ID_PRODUTO_VENDA, venda.getProdutoVenda().getId());
            cv.put(DataBase.IS_PARCELADO_VENDA, venda.isParcelado() ? 1 : 0);
            cv.put(DataBase.QUANTIDADE_PARCELAS_VENDA, venda.getQuantidadeParcelas());
            cv.put(DataBase.ID_CLIENTE_VENDA, venda.getClienteVenda().getId());


        } finally {
            close();
        }
    }

    public List<Venda> findAll() {

        try {
            open();

            return null;
        } finally {
            close();
        }
    }
}
