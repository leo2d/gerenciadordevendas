package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaClientesActivity extends AppCompatActivity {

    Button btnListarClientes;
    Button btnCadastrarCliente;

    private static final int TELA_CADASTRAR_CLIENTE = 1;
    private static final int TELA_LISTAR_CLIENTES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes);

        setTitle("Clientes");
        bind();

        btnCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), CadastroDeClientesActivity.class);
                startActivityForResult(itn, TELA_CADASTRAR_CLIENTE);
            }
        });

        btnListarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListaDeClientesActivity.class);
                startActivityForResult(itn, TELA_LISTAR_CLIENTES);
            }
        });
    }

    private void bind() {
        btnCadastrarCliente = findViewById(R.id.btnCadastrarCliente);
        btnListarClientes = findViewById(R.id.btnListarClientes);

    }
}
