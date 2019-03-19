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

        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();
            //Preciso pegar o Id do usuario aqui
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setTelefone(cursor.getString(2));
            cliente.setRG(cursor.getString(3));
            cliente.setCPF(cursor.getString(4));
            cliente.setEmail(cursor.getString(5));
            clientes.add(cliente);
        }

        return clientes;
    }

    public Cliente findById(int id) {
        // faz o select para procurar todos pelo ID

        String selectCliente = "SELECT * FROM " + DataBase.TABELA_CLIENTE + " WHERE " + DataBase.ID_CLIENTE + "==" + id + ";";
        Cursor cursor = conexao.rawQuery(selectCliente, null);

        Cliente cliente = new Cliente();

        //Percorre cada atributo de usuario
        if (cursor.moveToNext()) {
            cliente.setNome(cursor.getString(1));
            cliente.setTelefone(cursor.getString(2));
            cliente.setRG(cursor.getString(3));
            cliente.setCPF(cursor.getString(4));
            cliente.setEmail(cursor.getString(5));
        }
        return cliente;
    }
}
