package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.leonardo.gerenciadordevendas.entities.Cliente;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class TelaCadastroDeVendasActivity extends AppCompatActivity {

    public static final String TELA_CADASTRO_VENDAS = "Cadastro de Vendas do Usuario";
    public Cliente idCliente;

    EditText campoDataVenda;
    Switch switchParcela;
    Spinner spinnerParcela;
    Spinner spinnerProduto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_de_vendas);
        setTitle(TELA_CADASTRO_VENDAS);

        binding();

        //pegando o cliente selecionado
        idCliente = (Cliente) getIntent().getSerializableExtra(CHAVE_CLIENTE);

        Integer[] parcela = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.layout_spinner_parcelas, parcela);
        spinnerParcela.setAdapter(adapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_opcoes_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void binding() {
        campoDataVenda = findViewById(R.id.campoDataVenda);
        switchParcela = findViewById(R.id.switch1Parcela);
        spinnerParcela = findViewById(R.id.spinnerParcela);
        spinnerProduto = findViewById(R.id.spinnerProduto);
    }
}
