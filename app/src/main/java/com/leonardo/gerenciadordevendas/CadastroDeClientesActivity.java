package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;

public class CadastroDeClientesActivity extends AppCompatActivity {

    private static final String TELA_CADASTRO_CLIENTES = "Cadastro de Clientes";

    EditText campo_nome_cliente,
            campo_telefone_cliente,
            campo_cpf_cliente, campo_rg_cliente, campo_email_cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_clientes);
        setTitle(TELA_CADASTRO_CLIENTES);

        binding();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_cadastro_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.btn_menu_cadastrar_cliente) {
            //preciso passar todos os atributos dele
            //Direto do construtor

            Cliente cliente = new Cliente(campo_nome_cliente.getText().toString(),
                    campo_telefone_cliente.getText().toString(), campo_cpf_cliente.getText().toString(),
                    campo_rg_cliente.getText().toString(), campo_email_cliente.getText().toString());


            ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());
            clienteDAO.open();
            clienteDAO.gravarCliente(cliente);
            clienteDAO.close();
            finish();

        }
        return super.onOptionsItemSelected(item);
    }


    private void binding() {

        campo_nome_cliente = findViewById(R.id.campoNomeClienteCadastro);
        campo_telefone_cliente = findViewById(R.id.campoTelefoneClienteCadastro);
        campo_cpf_cliente = findViewById(R.id.campoCpfClienteCadastro);
        campo_rg_cliente = findViewById(R.id.campoRgClienteCadastro);
        campo_email_cliente = findViewById(R.id.campoEmailClienteCadastro);
    }
}
