package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.leonardo.gerenciadordevendas.DAO.CategoriaDAO;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.DAO.VendaDAO;
import com.leonardo.gerenciadordevendas.entities.*;

import java.util.ArrayList;
import java.util.List;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class TelaCadastroDeVendasActivity extends AppCompatActivity {

    public static final String TELA_CADASTRO_VENDAS = "Cadastro de Vendas";
    public static final int TELA_LISTA_VENDA = 1;
    public Cliente idCliente;

    FloatingActionButton buttonSave;
    TextView labelParcelas;
    EditText valorParcela;
    EditText valorVenda;
    EditText campoDataVenda;
    Switch switchParcela;
    Spinner spinnerParcela;
    Spinner spinnerProduto;
    Spinner spinnerCategorias;
    Spinner spinnerQuantidade;
    List<Produto> listaDeProduto;
    List<Categoria> listaDeCategorias;
    Venda venda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_de_vendas);
        setTitle(TELA_CADASTRO_VENDAS);

        binding();
        preencherSpinnerCategorias();

        //pegando o cliente selecionado
        idCliente = (Cliente) getIntent().getSerializableExtra(CHAVE_CLIENTE);

        venda = new Venda();
        venda.setClienteVenda(idCliente);
        venda.setIdCliente(idCliente.getId());

        //Generico, nao vindo do banco.
        final Integer[] parcela = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.layout_spinner_parcelas, parcela);
        spinnerParcela.setAdapter(adapter);

        ArrayAdapter<Integer> adapterQtd = new ArrayAdapter<Integer>(this, R.layout.spinner_item, parcela);
        spinnerQuantidade.setAdapter(adapterQtd);

        spinnerParcela.setEnabled(false);
        spinnerProduto.setEnabled(false);

        switchParcela.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spinnerParcela.setEnabled(switchParcela.isChecked());
            }
        });

        spinnerCategorias.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Categoria categoriaSelecionada = (Categoria) spinnerCategorias.getSelectedItem();

                preencheSpinnerProdutos(categoriaSelecionada.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spinnerProduto.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                atualizarValores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spinnerParcela.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                atualizarValores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidarCamposObrigatorios())
                    salvarVenda();
                finish();
            }
        });
    }

    private void preencherSpinnerCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO(this.getApplicationContext());

        try {
            categoriaDAO.open();
            listaDeCategorias = categoriaDAO.findAll();

            ArrayAdapter<Categoria> adapter =
                    new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaDeCategorias);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerCategorias.setAdapter(adapter);
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            categoriaDAO.close();
        }
    }


    private void binding() {
        buttonSave = findViewById(R.id.buttonSave);
        labelParcelas = findViewById(R.id.labelParcelas);
        valorParcela = findViewById(R.id.valorParcelado);
        valorVenda = findViewById(R.id.valorVenda);
        campoDataVenda = findViewById(R.id.campoDataVenda);
        switchParcela = findViewById(R.id.switch1Parcela);
        spinnerParcela = findViewById(R.id.spinnerParcela);
        spinnerProduto = findViewById(R.id.spinnerProduto);
        spinnerCategorias = findViewById(R.id.spinnerCategoria);
        spinnerQuantidade = findViewById(R.id.spinnerQuantidade);
    }

    private boolean ValidarCamposObrigatorios() {
        if (campoDataVenda.getText().toString().isEmpty() || campoDataVenda.getText() == null)
            return false;

        return true;
    }

    public void preencheSpinnerProdutos(int idCategoria) {

        ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());
        produtoDAO.open();

        listaDeProduto = produtoDAO.buscarPorCategoria(idCategoria);

        produtoDAO.close();

        ArrayAdapter<Produto> adapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaDeProduto);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProduto.setAdapter(adapter);
        spinnerProduto.setEnabled(!listaDeProduto.isEmpty());

        if (null == listaDeProduto || listaDeProduto.isEmpty()) {
            valorParcela.setText("R$ 00,00");
            valorVenda.setText("R$ 00,00");
            Toast.makeText(getApplicationContext(), "Nenhum produto encontrado para essa categoria.", Toast.LENGTH_LONG).show();
        }
    }

    private void salvarVenda() {
        venda.setDataVenda(campoDataVenda.getText().toString());

        try {
            VendaDAO vendaDAO = new VendaDAO(getApplicationContext());
            vendaDAO.gravarVenda(venda);


            for (Parcela p : venda.getParcelas()) {
                p.setIdVenda(venda.getId());
            }

            ParcelaDAO parcelaDAO = new ParcelaDAO(getApplicationContext());
            parcelaDAO.gravarParcelas(venda.getParcelas());
            //finish();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void atualizarValores() {
        venda.setParcelas(new ArrayList<Parcela>());

        int quantidadeParcelas = (int) spinnerParcela.getSelectedItem();

        venda.setQuantidadeParcelas(quantidadeParcelas);
        labelParcelas.setText(quantidadeParcelas + " x");

        Parcela parcelabase = null;

        Produto selecionado = (Produto) spinnerProduto.getSelectedItem();

        String baseValueText = "R$ ";

        if (null == selecionado) {
            valorParcela.setText(baseValueText + "00,00");
            valorVenda.setText(baseValueText + "00,00");
            return;
        }
        int dia = 07; //TODO ; arrumar isso aqui
        venda.gerarParcelas(dia, selecionado.getPreco());
        //  venda.setIdProduto(selecionado.getId());

        for (Parcela p : venda.getParcelas()) {
            parcelabase = p;
            break;
        }

        String textValorParcela = parcelabase.getValor() + "".replace(".", ",");

        valorParcela.setText(baseValueText + textValorParcela);
        valorVenda.setText(baseValueText + selecionado.getPreco());
    }

}
