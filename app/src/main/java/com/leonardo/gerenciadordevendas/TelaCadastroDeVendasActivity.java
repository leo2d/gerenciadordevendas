package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class TelaCadastroDeVendasActivity extends AppCompatActivity {

    public static final String TELA_CADASTRO_VENDAS = "Cadastro de Vendas";
    public Cliente idCliente;

    TextView labelParcelas;
    EditText valorParcela;
    EditText valorVenda;
    EditText campoDataVenda;
    Switch switchParcela;
    Spinner spinnerParcela;
    Spinner spinnerProduto;
    List<Produto> listaDeProduto;
    Venda venda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_de_vendas);
        setTitle(TELA_CADASTRO_VENDAS);

        binding();
        preencheSpinnerProdutos();


        //pegando o cliente selecionado
        idCliente = (Cliente) getIntent().getSerializableExtra(CHAVE_CLIENTE);

        venda = new Venda();
        venda.setClienteVenda(idCliente);

        //Generico, nao vindo do banco.
        final Integer[] parcela = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.layout_spinner_parcelas, parcela);
        spinnerParcela.setAdapter(adapter);

        spinnerParcela.setEnabled(false);

        switchParcela.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spinnerParcela.setEnabled(switchParcela.isChecked());
            }
        });

       try{

           spinnerProduto.setOnItemSelectedListener(new OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                   Produto selecionado = (Produto) spinnerProduto.getSelectedItem();
                   valorVenda.setText(selecionado.getPreco() + "");

                   Produto test = new Produto();
                   test.setId(selecionado.getId());
                   test.setPreco(selecionado.getPreco());
                   test.setDescricao(selecionado.getDescricao());
                   test.setTitulo(selecionado.getTitulo());

                  // venda.setProdutoVenda(test);
               }

               @Override
               public void onNothingSelected(AdapterView<?> parentView) {
                   // your code here
               }

           });

       }catch (Exception ex){
           System.out.println(ex.getMessage());
       }

        spinnerParcela.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int quantidadeParcelas = (int) spinnerParcela.getSelectedItem();
                int dia = Calendar.getInstance().getTime().getDay();


                venda.setQuantidadeParcelas(quantidadeParcelas);
                Parcela parcelabase = null;



                Produto selecionado = (Produto) spinnerProduto.getSelectedItem();
                venda.gerarParcelas(dia, selecionado.getPreco());

                for (Parcela p : venda.getParcelas()) {
                    parcelabase = p;
                    break;
                }

                valorParcela.setText(parcelabase.getValor() + "");
                labelParcelas.setText(quantidadeParcelas + " x");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }


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
        labelParcelas = findViewById(R.id.labelParcelas);
        valorParcela = findViewById(R.id.valorParcelado);
        valorVenda = findViewById(R.id.valorVenda);
        campoDataVenda = findViewById(R.id.campoDataVenda);
        switchParcela = findViewById(R.id.switch1Parcela);
        spinnerParcela = findViewById(R.id.spinnerParcela);
        spinnerProduto = findViewById(R.id.spinnerProduto);
    }

    public void preencheSpinnerProdutos() {

        ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());
        produtoDAO.open();

        listaDeProduto = produtoDAO.findAll();

        produtoDAO.close();

        ArrayAdapter<Produto> adapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaDeProduto);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProduto.setAdapter(adapter);

    }


}
