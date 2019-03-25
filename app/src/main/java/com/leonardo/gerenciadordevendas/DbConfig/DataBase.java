package com.leonardo.gerenciadordevendas.DbConfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "banco.db";
    private static final int DATA_BASE_VERSION = 1;

    //TABELA CLIENTE

    public static final String TABELA_CLIENTE = "tbl_cliente";

    public static final String ID_CLIENTE = "idCliente";
    public static final String NOME_CLIENTE = "nomeCliente";
    public static final String TELEFONE_CLIENTE = "telefoneCliente";
    public static final String RG_CLIENTE = "rgCliente";
    public static final String CPF_CLIENTE = "cpfCliente";
    public static final String EMAIL_CLIENTE = "emailCliente";
    //ligação
    //public static final String ID_VENDA_CLIENTE = "idVendaCliente";

    //-----------------------------------------------------------------------

    //TABELA PARCELA
    public static final String TABELA_PARCELA = "tbl_parcela";

    public static final String ID_PARCELA = "idParcela";
    public static final String FOI_PAGA_PARCELA = "foiPagaParcela";
    public static final String DIA_VENCIMENTO_PARCELA = "diaVencimento";
    public static final String VALOR_PARCELA = "valorParcela";
    //ligação
    public static final String ID_VENDA_PARCELA = "idVendaParcela";


    //-----------------------------------------------------------------------

    //TABELA PRODUTO

    public static final String TABELA_PRODUTO = "tbl_produto";

    public static final String ID_PRODUTO = "idProduto";
    public static final String TITULO_PRODUTO = "tituloProduto";
    public static final String DESCRICAO_PRODUTO = "descricaoProduto";
    public static final String PRECO_PRODUTO = "precoProduto";
    public static final String ID_CATEGORIA_PRODUTO = "IdCategoria_produto";


    //-----------------------------------------------------------------------

    //TABELA VENDA

    public static final String TABELA_VENDA = "tbl_venda";

    public static final String ID_VENDA = "idVenda";
    public static final String DATA_VENDA = "dataVenda";
    public static final String IS_PARCELADO_VENDA = "isParcelado";
    public static final String QUANTIDADE_PARCELAS_VENDA = "quantidadeParcela";
    //ligação
    public static final String ID_PRODUTO_VENDA = "idProdutovenda";
    public static final String ID_CLIENTE_VENDA = "iClienteVenda";


    //-----------------------------------------------------------------------

    //TABELA CATEGORIA

    public static final String TABELA_CATEGORIA = "tbl_categoria";

    public static final String ID_CATEGORIA = "idCategoria";
    public static final String NOME_CATEGORIA = "nomeCategoria";


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
            PRECO_PRODUTO + " text not null, " +
            ID_CATEGORIA_PRODUTO + " integer " +
            ");";

    String tblVenda = "CREATE TABLE " + TABELA_VENDA + " (" +
            ID_VENDA + " integer primary key autoincrement," +
            IS_PARCELADO_VENDA + " text not null," +
            DATA_VENDA + " text not null," +
            QUANTIDADE_PARCELAS_VENDA + " text not null," +
            ID_PRODUTO_VENDA + " integer, " +
            ID_CLIENTE_VENDA + " integer " +
            ");";


    String tblCategoria = "CREATE TABLE " + TABELA_CATEGORIA + " (" +
            ID_CATEGORIA + " integer primary key autoincrement," +
            NOME_CATEGORIA + " text not null" +
            ");";


    String insertCategoria = "INSERT INTO " + DataBase.TABELA_CATEGORIA + " (" +
            " " + DataBase.ID_CATEGORIA + ", " + DataBase.NOME_CATEGORIA + " ) " +
            " VALUES (1, 'Fonte'), (2, 'Memoria RAM'), (3, 'Processador'), (4, 'Gabinete'), " +
            "(5, 'Placa de Video'), (6,'Placa Mae'), (7, 'Perifericos'), (8,'Disco Rigido');";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tblCliente);
        db.execSQL(tblParcela);
        db.execSQL(tblProduto);
        db.execSQL(tblVenda);
        db.execSQL(tblCategoria);

        db.execSQL(insertCategoria);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABELA_CLIENTE);
        db.execSQL("drop table " + TABELA_PARCELA);
        db.execSQL("drop table " + TABELA_PRODUTO);
        db.execSQL("drop table " + TABELA_VENDA);
        db.execSQL("drop table " + TABELA_CATEGORIA);
        onCreate(db);
    }
}
