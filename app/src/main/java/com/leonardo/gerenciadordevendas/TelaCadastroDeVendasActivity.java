package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.leonardo.gerenciadordevendas.entities.Cliente;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class TelaCadastroDeVendasActivity extends AppCompatActivity {

    public static final String TELA_CADASTRO_VENDAS = "Cadastro de Vendas do Usuario";
    public Cliente idCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_de_vendas);
        setTitle(TELA_CADASTRO_VENDAS);

        binding();

        //pegando o cliente selecionado
        idCliente = (Cliente) getIntent().getSerializableExtra(CHAVE_CLIENTE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_opcoes_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void binding() {
    }
}
