package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaProdutosActivity extends AppCompatActivity {

    private static final String TELA_PRODUTO = "Tela Produtos";
    private static final int TELA_CADASTRO_PRODUTO = 1;
    private static final int TELA_LISTA_PRODUTO = 2;
    Button btn_tela_cadastro_produto, btn_tela_lista_produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produtos);
        setTitle(TELA_PRODUTO);

        binding();

        btn_tela_cadastro_produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), TelaCadastroProdutoActivity.class);
                startActivityForResult(itn, TELA_CADASTRO_PRODUTO);
            }
        });

        btn_tela_lista_produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),TelaListaProdutoActivity.class);
                startActivityForResult(itn, TELA_LISTA_PRODUTO);
            }
        });

    }

    private void binding() {
        btn_tela_cadastro_produto =  findViewById(R.id.btnTelaCadastroProduto);
        btn_tela_lista_produto =  findViewById(R.id.btnTelaListaProduto);
    }
}
