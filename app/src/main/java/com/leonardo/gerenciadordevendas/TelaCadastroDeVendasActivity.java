package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class TelaCadastroDeVendasActivity extends AppCompatActivity {

    public static final String TELA_CADASTRO_VENDAS = "Cadastro de Vendas do Usuario";
    public Cliente idCliente;

    EditText campoDataVenda;
    Switch switchParcela;
    Spinner spinnerParcela;
    Spinner spinnerProduto;
    List<Produto> listaDeProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_de_vendas);
        setTitle(TELA_CADASTRO_VENDAS);

        binding();
        preencheSpinnerProdutos();

        //pegando o cliente selecionado
        idCliente = (Cliente) getIntent().getSerializableExtra(CHAVE_CLIENTE);

        //Generico, nao vindo do banco.
        Integer[] parcela = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.layout_spinner_parcelas, parcela);
        spinnerParcela.setAdapter(adapter);
    }


//    public void verificaSwtch() {
//        if (switchParcela.isChecked()) {
//            spinnerParcela.setVisibility(View.GONE);
//        } else {
//            spinnerParcela.setVisibility(View.INVISIBLE);
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_opcoes_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_menu_cadastrar_venda) {

            //cadastro a venda aqui
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void binding() {
        campoDataVenda = findViewById(R.id.campoDataVenda);
        switchParcela = findViewById(R.id.switch1Parcela);
        spinnerParcela = findViewById(R.id.spinnerParcela);
        spinnerProduto = findViewById(R.id.spinnerProduto);
    }

    public void preencheSpinnerProdutos() {

        ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());
        produtoDAO.open();

        int idx = 0;
        listaDeProduto = produtoDAO.findAll();
        String[] produtos = new String[listaDeProduto.size()];
        for (Produto p : listaDeProduto) {
            produtos[idx] = p.getTitulo();
            idx++;
        }
        produtoDAO.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_spinner, produtos);
        spinnerProduto.setAdapter(adapter);
    }
}
