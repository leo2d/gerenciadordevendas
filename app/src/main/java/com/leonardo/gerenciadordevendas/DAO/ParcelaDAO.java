package com.leonardo.gerenciadordevendas.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Parcela;

public class ParcelaDAO {

    private SQLiteDatabase conexao;
    private DataBase dataBase;
    private Context context;
    private Parcela parcela;

    public ParcelaDAO(Context context) {
        dataBase = new DataBase(context);
        this.context = context;
    }

    public void open() {
        conexao = dataBase.getWritableDatabase();
    }

    public void close() {
        conexao.close();
    }

    public void gravarParcela(Parcela parcela) {
        String command = "INSERT INTO " + DataBase.TABELA_PARCELA + " (" + DataBase.FOI_PAGA_PARCELA + ", "
                + DataBase.DIA_VENCIMENTO_PARCELA + ", " + DataBase.VALOR_PARCELA + ", " + DataBase.ID_VENDA_PARCELA
                + " VALUES (" + parcela.getIndicadorPagamento() + ", '" + parcela.getDiaVencimento() + "' , '" + parcela.getValor()
                + "', " + parcela.getIdVenda() + ");";

        conexao.execSQL(command);
    }

}
