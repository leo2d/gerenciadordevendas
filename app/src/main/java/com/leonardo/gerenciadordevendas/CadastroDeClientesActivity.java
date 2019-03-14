package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CadastroDeClientesActivity extends AppCompatActivity {

    private static final String TELA_CADASTRO_CLIENTES = "Cadastro de Clientes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_clientes);
        setTitle(TELA_CADASTRO_CLIENTES);
    }
}
