package com.leonardo.gerenciadordevendas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int TELA_CLIENTES = 1;
    private static final int TELA_PRODUTOS = 2;
    private static final int TELA_VENDAS = 3;

    Button btnClientes, btnProdutos, btnVendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding();

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), TelaClientesActivity.class);
                startActivityForResult(itn, TELA_CLIENTES);
            }
        });

        btnProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), TelaProdutosActivity.class);
                startActivityForResult(itn, TELA_PRODUTOS);
            }
        });

        btnVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), TelaVendasActivity.class);
                startActivityForResult(itn, TELA_VENDAS);
            }
        });

    }

    public void sairApp(View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Sair");
        dialog.setMessage("Deseja sair do app?");

        //configura o cancelamento.
        dialog.setCancelable(false);
        //icone
        dialog.setIcon(android.R.drawable.ic_dialog_info);

        //Ações
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //criar e exibir
        dialog.create();
        dialog.show();
    }

    private void binding() {
        btnClientes = findViewById(R.id.btnClientes);
        btnProdutos = findViewById(R.id.btnProdutos);
        btnVendas = findViewById(R.id.btnVendas);
    }
}
