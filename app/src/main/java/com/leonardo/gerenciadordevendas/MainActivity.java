package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_cadastrar_cliente,
            btn_produtos,btn_vendas,
            btn_sair_app, btn_lista_de_clientes;
    public static final int TELA_CADASTRO_CLIENTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding();

        btn_cadastrar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), CadastroDeClientesActivity.class);
                startActivityForResult(itn, TELA_CADASTRO_CLIENTE);
            }
        });
    }

    private void binding() {
        btn_cadastrar_cliente = findViewById(R.id.btnCadastrarCliente);
        btn_produtos = findViewById(R.id.btnProdutos);
        btn_vendas = findViewById(R.id.btnVendas);
        btn_sair_app = findViewById(R.id.btnSair);
        btn_lista_de_clientes = findViewById(R.id.btnListaDeCliente);
    }
}
