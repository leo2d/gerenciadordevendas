package com.leonardo.gerenciadordevendas.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonardo.gerenciadordevendas.DbConfig.DataBase;
import com.leonardo.gerenciadordevendas.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context contexto;

    public ClienteDAO(Context context) {
        banco = new DataBase(context);
        this.contexto = context;
    }

    public void open() {
        conexao = banco.getWritableDatabase();
    }

    public void close() {
        conexao.close();
    }

    public void gravarCliente(Cliente cliente) {

        String insertUsuario = "insert into " + DataBase.TABELA_CLIENTE + " ( "
                + DataBase.NOME_CLIENTE + ", " + DataBase.TELEFONE_CLIENTE + ", " + DataBase.RG_CLIENTE
                + ", " + DataBase.CPF_CLIENTE + ", " + DataBase.EMAIL_CLIENTE + ")"
                + " values ('" + cliente.getNome() + "', '" + cliente.getTelefone() + "', '" + cliente.getRG()
                + "', '" + cliente.getCPF() + "', '" + cliente.getEmail() + "');";

        // passa os parametros da tabela que voce vai gravar
        // abre a conexao com o banco para gravar o usuario
        conexao.execSQL(insertUsuario);
    }

    public List<Cliente> findAll() {
        String selectusuario = "SELECT * FROM " + DataBase.TABELA_CLIENTE + " ;";

        Cursor cursor = conexao.rawQuery(selectusuario, null);
        // Como estou pegando todos os usuarios, usei a Lis encima
        //para percorre-los, eu crio um ArrayList de Usuarios.
        // Ou sej, vamos ter um While ao inves de if
        // Pois enquanto tiver usuarios na lista eu vou percorrer
        ArrayList<Cliente> clientes = new ArrayList<>();

        // cargo.setId(c.getInt(c.getColumnIndex(DataBase.FIELD_CARGO_ID)));

        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();
            cliente.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_CLIENTE)));
            cliente.setNome(cursor.getString(cursor.getColumnIndex(DataBase.NOME_CLIENTE)));
            cliente.setTelefone(cursor.getString(cursor.getColumnIndex(DataBase.TELEFONE_CLIENTE)));
            cliente.setRG(cursor.getString(cursor.getColumnIndex(DataBase.RG_CLIENTE)));
            cliente.setCPF(cursor.getString(cursor.getColumnIndex(DataBase.CPF_CLIENTE)));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex(DataBase.EMAIL_CLIENTE)));
            clientes.add(cliente);
        }

        return clientes;
    }

    public Cliente findById(int id) {
        // faz o select para procurar todos pelo ID

        open();

        String selectCliente =
                "SELECT * FROM " + DataBase.TABELA_CLIENTE +
                        " WHERE " + DataBase.ID_CLIENTE + "=" + id + ";";

        Cursor cursor = conexao.rawQuery(selectCliente, null);

        Cliente cliente = new Cliente();

        if (cursor.moveToNext()) {
            cliente.setId(cursor.getInt(cursor.getColumnIndex(DataBase.ID_CLIENTE)));
            cliente.setNome(cursor.getString(cursor.getColumnIndex(DataBase.NOME_CLIENTE)));
            cliente.setTelefone(cursor.getString(cursor.getColumnIndex(DataBase.TELEFONE_CLIENTE)));
            cliente.setRG(cursor.getString(cursor.getColumnIndex(DataBase.RG_CLIENTE)));
            cliente.setCPF(cursor.getString(cursor.getColumnIndex(DataBase.CPF_CLIENTE)));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex(DataBase.EMAIL_CLIENTE)));
        }

        close();

        return cliente;
    }
}
