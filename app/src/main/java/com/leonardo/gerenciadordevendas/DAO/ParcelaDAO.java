package com.leonardo.gerenciadordevendas.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Categoria;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.ArrayList;
import java.util.List;

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
        try {
            this.open();

            String command = "INSERT INTO " + DataBase.TABELA_PARCELA + " (" + DataBase.FOI_PAGA_PARCELA + ", "
                    + DataBase.DIA_VENCIMENTO_PARCELA + ", " + DataBase.VALOR_PARCELA + ", " + DataBase.ID_VENDA_PARCELA
                    + " VALUES (" + parcela.getIndicadorPagamento() + ", '" + parcela.getDiaVencimento() + "' , '" + parcela.getValor()
                    + "', " + parcela.getIdVenda() + ");";

            conexao.execSQL(command);

        } catch (Exception ex) {

        } finally {
            this.close();
        }
    }

    public void gravarParcelas(List<Parcela> parcelas) {
        try {
            this.open();
            for (Parcela parcela : parcelas) {
                String command = "INSERT INTO " + DataBase.TABELA_PARCELA + " (" + DataBase.FOI_PAGA_PARCELA + ", "
                        + DataBase.DIA_VENCIMENTO_PARCELA + ", " + DataBase.VALOR_PARCELA + ", " + DataBase.ID_VENDA_PARCELA
                        + " VALUES (" + parcela.getIndicadorPagamento() + ", '" + parcela.getDiaVencimento() + "' , '" + parcela.getValor()
                        + "', " + parcela.getIdVenda() + ");";

                conexao.execSQL(command);
            }
        } finally {
            this.close();
        }
    }

    private String obterQuerybase() {
        return "SELECT parcela.* FROM " + DataBase.TABELA_PARCELA + "parcela "
                + "INNER JOIN " + DataBase.TABELA_VENDA + " venda "
                + "ON venda." + DataBase.ID_VENDA + " = parcela." + DataBase.ID_VENDA_PARCELA
                + " WHERE 1=1;";
    }

    public List<Parcela> getAll() {

        try {
            open();

            String query = obterQuerybase();

            Cursor cursor = conexao.rawQuery(query, null);
            List<Parcela> parcelas = new ArrayList<>();

            while (cursor.moveToNext()) {

                Parcela parcela = new Parcela();
                parcela.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PARCELA)));
                parcela.setIndicadorPagamento(cursor.getInt(cursor.getColumnIndex(DataBase.FOI_PAGA_PARCELA)));
                parcela.setDiaVencimento(cursor.getString(cursor.getColumnIndex(DataBase.DIA_VENCIMENTO_PARCELA)));
                parcela.setIdVenda(cursor.getInt(cursor.getColumnIndex(DataBase.ID_VENDA_PARCELA)));
                parcela.setValor(cursor.getDouble(cursor.getColumnIndex(DataBase.VALOR_PARCELA)));
                parcelas.add(parcela);
            }

            return parcelas;

        } finally {
            close();
        }
    }

    public List<Parcela> buscarPorVenda(int idVenda) {

        try {
            open();

            String query = obterQuerybase();
            query += " AND parcela." + DataBase.ID_VENDA_PARCELA + " = " + idVenda;

            Cursor cursor = conexao.rawQuery(query, null);
            List<Parcela> parcelas = new ArrayList<>();

            while (cursor.moveToNext()) {

                Parcela parcela = new Parcela();
                parcela.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_PARCELA)));
                parcela.setIndicadorPagamento(cursor.getInt(cursor.getColumnIndex(DataBase.FOI_PAGA_PARCELA)));
                parcela.setDiaVencimento(cursor.getString(cursor.getColumnIndex(DataBase.DIA_VENCIMENTO_PARCELA)));
                parcela.setIdVenda(cursor.getInt(cursor.getColumnIndex(DataBase.ID_VENDA_PARCELA)));
                parcela.setValor(cursor.getDouble(cursor.getColumnIndex(DataBase.VALOR_PARCELA)));
                parcelas.add(parcela);
            }

            return parcelas;

        } finally {
            close();
        }
    }

}
