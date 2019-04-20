package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.leonardo.gerenciadordevendas.Enums.TelaDeOrigemEnum;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.TELA_ORIGEM;

public class TelaVendasActivity extends AppCompatActivity {

    Button btnListarVendas;
    Button btnCadastrarVenda;

    private static final int TELA_CADASTRAR_VENDA = 1;
    private static final int TELA_LISTA_VENDAS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_vendas);

        setTitle("Vendas");

        bind();

        btnCadastrarVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListaDeClientesActivity.class);
                itn.putExtra(TELA_ORIGEM, TelaDeOrigemEnum.MenuVendas.getValue());
                startActivityForResult(itn, TELA_CADASTRAR_VENDA);
            }
        });

        btnListarVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListaVendaActivity.class);
                startActivityForResult(itn, TELA_LISTA_VENDAS);
            }
        });
    }

    private void bind() {
        btnCadastrarVenda = findViewById(R.id.btnCadastrarVenda);
        btnListarVendas = findViewById(R.id.btnListarVendas);

    }
}
