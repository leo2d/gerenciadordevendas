package com.leonardo.gerenciadordevendas.DbConfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "banco.db";
    private static final int DATA_BASE_VERSION = 1;

    //TABELA CLIENTE

    private static final String TABELA_CLIENTE = "tbl_cliente";

    private static final String ID_CLIENTE = "idCliente";
    private static final String NOME_CLIENTE = "nomeCliente";
    private static final String TELEFONE_CLIENTE = "telefoneCliente";
    private static final String RG_CLIENTE = "rgCliente";
    private static final String CPF_CLIENTE = "cpfCliente";
    private static final String EMAIL_CLIENTE = "emailCliente";
    //ligação
    private static final String ID_VENDA_CLIENTE = "idVendaCliente";

    //-----------------------------------------------------------------------

    //TABELA PARCELA
    private static final String TABELA_PARCELA = "tbl_parcela";

    private static final String ID_PARCELA = "idParcela";
    private static final String FOI_PAGA_PARCELA = "foiPagaParcela";
    private static final String DIA_VENCIMENTO_PARCELA = "diaVencimento";
    private static final String VALOR_PARCELA = "valorParcela";
    //ligação
    private static final String ID_VENDA_PARCELA = "idVendaParcela";


    //-----------------------------------------------------------------------

    //TABELA PRODUTO

    private static final String TABELA_PRODUTO = "tbl_produto";

    private static final String ID_PRODUTO = "idProduto";
    private static final String TITULO_PRODUTO = "tituloProduto";
    private static final String DESCRICAO_PRODUTO = "descricaoProduto";
    private static final String PRECO_PRODUTO = "precoProduto";


    //-----------------------------------------------------------------------

    //TABELA VENDA

    private static final String TABELA_VENDA = "tbl_venda";

    private static final String ID_VENDA = "idVenda";
    private static final String IS_PARCELADO_VENDA = "isParcelado";
    private static final String QUANTIDADE_PARCELAS_VENDA = "quantidadeParcela";
    //ligação
    private static final String ID_PRODUTO_VENDA = "idProdutovenda";
    private static final String ID_CLIENTE_VENDA = "iClienteVenda";


    public DataBase(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    //CRIAÇÃO DAS TABELAS

    String tblCliente = "CREATE TABLE " + TABELA_CLIENTE + " (" +
            ID_CLIENTE + " integer primary key autoincrement, " +
            NOME_CLIENTE + " text not null, " +
            TELEFONE_CLIENTE + " text not null, " +
            CPF_CLIENTE + " text not null, " +
            RG_CLIENTE + " text not null, " +
            EMAIL_CLIENTE + " text not null " +
            ");";

    String tblParcela = "CREATE TABLE " + TABELA_PARCELA + " (" +
            ID_PARCELA + " integer primary key autoincrement," +
            FOI_PAGA_PARCELA + " text not null," +
            DIA_VENCIMENTO_PARCELA + " text not null," +
            VALOR_PARCELA + " text not null," +
            ID_VENDA_PARCELA + " integer " +
            ");";


    String tblProduto = "CREATE TABLE " + TABELA_PRODUTO + " (" +
            ID_PRODUTO + " integer primary key autoincrement," +
            TITULO_PRODUTO + " text not null," +
            DESCRICAO_PRODUTO + " text not null," +
            PRECO_PRODUTO + " text not null " +
            ");";

    String tblVenda = "CREATE TABLE " + TABELA_VENDA + " (" +
            ID_VENDA + " integer primary key autoincrement," +
            IS_PARCELADO_VENDA + " text not null," +
            QUANTIDADE_PARCELAS_VENDA + " text not null," +
            ID_PRODUTO_VENDA + " integer, " +
            ID_CLIENTE_VENDA + " integer " +
            ");";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tblCliente);
        db.execSQL(tblParcela);
        db.execSQL(tblProduto);
        db.execSQL(tblVenda);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABELA_CLIENTE);
        db.execSQL("drop table " + TABELA_PARCELA);
        db.execSQL("drop table " + TABELA_PRODUTO);
        db.execSQL("drop table " + TABELA_VENDA);
        onCreate(db);
    }
}
