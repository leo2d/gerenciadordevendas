package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

public class DetalharVendaActivity extends AppCompatActivity {

    public static final String VENDA_DETALHADA = "Venda Nro ";

    Venda venda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_venda);

        setTitle(VENDA_DETALHADA + 666);


    }
}
