package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.leonardo.gerenciadordevendas.DAO.CategoriaDAO;
import com.leonardo.gerenciadordevendas.DAO.ItemVendaDAO;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.DAO.VendaDAO;
import com.leonardo.gerenciadordevendas.entities.Categoria;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.ItemVenda;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Produto;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.ArrayList;
import java.util.List;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class TelaCadastroDeVendasActivity extends AppCompatActivity {

    public static final String TELA_CADASTRO_VENDAS = "Cadastro de Vendas";
    public static final int MAIN_MENU = 1;
    public Cliente idCliente;

    FloatingActionButton buttonSave;
    Button buttonAddProduto;

    TextView pagamentoLabel;
    Switch switchPagamentoVista;
    EditText valorParcela;
    EditText valorVenda;
    EditText campoDataVenda;
    Spinner spinnerParcela;
    Spinner spinnerProduto;
    Spinner spinnerCategorias;
    Spinner spinnerQuantidade;
    List<Produto> listaDeProduto;
    List<Categoria> listaDeCategorias;
    List<ItemVenda> itensVenda;
    Venda venda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_de_vendas);
        setTitle(TELA_CADASTRO_VENDAS);

        binding();
        desabilitarComponentes();
        preencherSpinnerCategorias();

        //pegando o cliente selecionado
        idCliente = (Cliente) getIntent().getSerializableExtra(CHAVE_CLIENTE);

        venda = new Venda();
        venda.setClienteVenda(idCliente);
        venda.setIdCliente(idCliente.getId());

        preencherSpinnersFixos();
        tratarEventos();

    }

    private void binding() {
        buttonSave = findViewById(R.id.buttonSave);
        valorParcela = findViewById(R.id.valorParcelado);
        valorVenda = findViewById(R.id.valorVenda);
        campoDataVenda = findViewById(R.id.campoDataVenda);
        spinnerParcela = findViewById(R.id.spinnerParcela);
        spinnerProduto = findViewById(R.id.spinnerProduto);
        spinnerCategorias = findViewById(R.id.spinnerCategoria);
        spinnerQuantidade = findViewById(R.id.spinnerQuantidade);
        buttonAddProduto = findViewById(R.id.buttonAddProduto);
        switchPagamentoVista = findViewById(R.id.switchPagamentoVista);
        pagamentoLabel = findViewById(R.id.pagamentoLabel);

    }

    private void tratarEventos() {
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

                //atualizarValores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spinnerParcela.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int quantidade = (int) spinnerParcela.getSelectedItem();
                if (quantidade > 1) {
                    switchPagamentoVista.setChecked(false);
                    switchPagamentoVista.setEnabled(false);
                }else{
                    switchPagamentoVista.setEnabled(venda.getItens().size() > 0);
                }

                atualizarValores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        buttonAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adicionarItemNaVenda();
                atualizarValores();

                spinnerParcela.setEnabled(!switchPagamentoVista.isChecked());

                int quantidade = (int) spinnerParcela.getSelectedItem();

                switchPagamentoVista.setEnabled(quantidade == 1);
                switchPagamentoVista.setChecked(quantidade > 1 ? false : switchPagamentoVista.isChecked() );
                buttonSave.setEnabled(true);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidarCamposObrigatorios()) {
                    salvarVenda();
                    Intent itn = new Intent(getApplicationContext(), ListaVendaActivity.class);
                    startActivityForResult(itn, MAIN_MENU);
                } else {
                    Toast.makeText(getBaseContext(), "Preencha todos os campos antes de salvar!", Toast.LENGTH_LONG).show();
                }
            }
        });

        switchPagamentoVista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    pagamentoLabel.setText("Sim");
                    spinnerParcela.setEnabled(false);
                } else {
                    pagamentoLabel.setText("Não");
                    spinnerParcela.setEnabled(venda.getItens().size() > 0);
                }
            }
        });
        ;
    }

    private void preencherSpinnersFixos() {
        //Generico, nao vindo do banco.
        final Integer[] quantidades = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.spinner_item, quantidades);
        spinnerParcela.setAdapter(adapter);

        ArrayAdapter<Integer> adapterQtd = new ArrayAdapter<Integer>(this, R.layout.spinner_item, quantidades);
        spinnerQuantidade.setAdapter(adapterQtd);
    }

    private void desabilitarComponentes() {
        switchPagamentoVista.setEnabled(false);
        spinnerParcela.setEnabled(false);
        spinnerProduto.setEnabled(false);
        spinnerQuantidade.setEnabled(false);
        buttonSave.setEnabled(false);
        buttonAddProduto.setEnabled(false);
    }

    private void ativarComponentes() {

        spinnerProduto.setEnabled(true);
        spinnerQuantidade.setEnabled(true);
        buttonAddProduto.setEnabled(true);
    }

    private void preencherSpinnerCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO(this.getApplicationContext());

        try {
            categoriaDAO.open();
            listaDeCategorias = categoriaDAO.findAll();

            ArrayAdapter<Categoria> adapter =
                    new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, listaDeCategorias);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerCategorias.setAdapter(adapter);
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            categoriaDAO.close();
        }
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
                new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, listaDeProduto);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProduto.setAdapter(adapter);

        if (null == listaDeProduto || listaDeProduto.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nenhum produto encontrado para essa categoria.", Toast.LENGTH_LONG).show();
        } else {
            ativarComponentes();
        }
    }

    private void zerarValoresLabels() {
        valorParcela.setText("R$ 00,00");
        valorVenda.setText("R$ 00,00");
    }

    private void atualizarValores() {
        venda.setParcelas(new ArrayList<Parcela>());

        int quantidadeParcelas = (int) spinnerParcela.getSelectedItem();

        venda.setQuantidadeParcelas(quantidadeParcelas);

        Parcela parcelabase = null;

        String baseValueText = "R$ ";

        if (venda.getItens().isEmpty()) {
            zerarValoresLabels();
            return;
        }
        int dia = 07; //TODO ; arrumar isso aqui

        venda.gerarParcelas(dia);

        for (Parcela p : venda.getParcelas()) {
            parcelabase = p;
            break;
        }

        String textValorParcela = parcelabase.getValor() + "".replace(".", ",");

        valorParcela.setText(baseValueText + textValorParcela);
        valorVenda.setText(baseValueText + venda.getQuantidadeParcelas() * parcelabase.getValor());
    }

    private void adicionarItemNaVenda() {
        int quantidade = (int) spinnerQuantidade.getSelectedItem();
        Produto produtoSelecionado = (Produto) spinnerProduto.getSelectedItem();

        ItemVenda itemVenda = new ItemVenda(produtoSelecionado.getId(), quantidade, produtoSelecionado);

        venda.adicionarItemVenda(itemVenda);
    }

    private void salvarVenda() {
        venda.setDataVenda(campoDataVenda.getText().toString());

        try {
            VendaDAO vendaDAO = new VendaDAO(getApplicationContext());
            vendaDAO.gravarVenda(venda);

            for (ItemVenda item : venda.getItens()) {
                item.setIdVenda(venda.getId());
            }

            ItemVendaDAO itemVendaDAO = new ItemVendaDAO(getApplicationContext());
            itemVendaDAO.gravarItensVenda(venda.getItens());

            for (Parcela p : venda.getParcelas()) {
                p.setIdVenda(venda.getId());

                if (switchPagamentoVista.isChecked() && venda.getParcelas().size() == 1) {
                    p.setFoiPaga(true);
                    p.setIndicadorPagamento(1);
                }
            }

            ParcelaDAO parcelaDAO = new ParcelaDAO(getApplicationContext());
            parcelaDAO.gravarParcelas(venda.getParcelas());

            Toast.makeText(getBaseContext(), "Venda salva com sucesso!", Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
