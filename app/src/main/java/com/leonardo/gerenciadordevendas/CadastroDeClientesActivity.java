package com.leonardo.gerenciadordevendas;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;

public class CadastroDeClientesActivity extends AppCompatActivity {

    private static final String TELA_CADASTRO_CLIENTES = "Cadastro de Clientes";

    EditText campo_nome_cliente,
            campo_telefone_cliente,
            campo_cpf_cliente, campo_rg_cliente, campo_email_cliente;

    FloatingActionButton buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_clientes);
        setTitle(TELA_CADASTRO_CLIENTES);

        binding();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidarCamposObrigatorios())
                    salvarCliente();
                else
                    Toast.makeText(getBaseContext(), "Preencha NOME, TELEFONE e CPF antes de salvar!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean ValidarCamposObrigatorios() {
        if (campo_nome_cliente.getText() == null ||
                campo_nome_cliente.getText().toString().trim().isEmpty() ||
                campo_telefone_cliente.getText() == null ||
                campo_telefone_cliente.getText().toString().trim().isEmpty() ||
                campo_cpf_cliente.getText() == null ||
                campo_cpf_cliente.getText().toString().trim().isEmpty()
        ) {
            return false;
        } else {
            return true;
        }
    }

    private void salvarCliente() {

        Cliente cliente = new Cliente(campo_nome_cliente.getText().toString(),
                campo_telefone_cliente.getText().toString(), campo_cpf_cliente.getText().toString(),
                campo_rg_cliente.getText().toString(), campo_email_cliente.getText().toString());


        ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());
        clienteDAO.open();
        clienteDAO.gravarCliente(cliente);
        clienteDAO.close();
        finish();
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
            if (ValidarCamposObrigatorios())
                salvarCliente();
            else
                Toast.makeText(getBaseContext(), "Preencha NOME, TELEFONE e CPF antes de salvar!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void binding() {
        buttonSave = findViewById(R.id.buttonSave);
        campo_nome_cliente = findViewById(R.id.campoNomeClienteCadastro);
        campo_telefone_cliente = findViewById(R.id.campoTelefoneClienteCadastro);
        campo_cpf_cliente = findViewById(R.id.campoCpfClienteCadastro);
        campo_rg_cliente = findViewById(R.id.campoRgClienteCadastro);
        campo_email_cliente = findViewById(R.id.campoEmailClienteCadastro);
    }
}
