package com.leonardo.gerenciadordevendas;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.Helpers.MoneyHelper;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_VENDA;
import static com.leonardo.gerenciadordevendas.TelaCadastroDeVendasActivity.MAIN_MENU;

public class DetalharVendaActivity extends AppCompatActivity {

    public static final String VENDA_DETALHADA = "Venda Nro:  ";
    public static final String SIMBOLO_REAL = "R$ ";

    FloatingActionButton buttonSave;
    Venda venda;

    TextView campoDataVenda;
    //EditText cpfCliente, telefoneCliente, nomeCliente;
    TextView cpfCliente, telefoneCliente, nomeCliente;
    TextView valorDivida, valorVenda;
    Spinner spinnerPagarParcelas;
    double valorRestante;

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_venda);

        venda = (Venda) getIntent().getSerializableExtra(CHAVE_VENDA);
        setTitle(VENDA_DETALHADA + venda.getId());

        popularCliente();
        popularParcelas();

        bind();
        valorDivida.setInputType(InputType.TYPE_NULL);
        valorVenda.setInputType(InputType.TYPE_NULL);

        preencherCampos();
        PreencherSpinnerparcelas();

        TratarEventos();
        closseKeyboard();

    }

    private void closseKeyboard() {
        View view = this.getCurrentFocus();
        if (null != view) {
            InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void TratarEventos() {
        spinnerPagarParcelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (++check > 1) {
                    int quantidade = (int) spinnerPagarParcelas.getSelectedItem();

                    double valorparcela = venda.getParcelasEmDebito().get(0).getValor();
                    valorRestante = venda.calcularTotalDevedor() - (quantidade * valorparcela);

                    valorDivida.setText(MoneyHelper.formatarEmReal(valorRestante));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantidade = (int) spinnerPagarParcelas.getSelectedItem();

                if (quantidade > 0) {
                    pagarParcela(quantidade);
                    Intent itn = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(itn, MAIN_MENU);
                } else {
                    Toast.makeText(getBaseContext(), "Selecione pelo menos uma parcela", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void pagarParcela(int quantidade) {

        List<Parcela> parcelasAPagar = venda.getParcelasEmDebito();

        for(int i = 0; i< quantidade; i++){
             parcelasAPagar.get(i).setFoiPaga(true);
        }

        ParcelaDAO parcelaDAO = new ParcelaDAO(this);
        parcelaDAO.atualizarParcelas(parcelasAPagar);
    }

    private void PreencherSpinnerparcelas() {
        int quantidadeParcelasEmDebito = venda.getParcelasEmDebito().size();

        Integer[] quantidadeEmDebito = new Integer[quantidadeParcelasEmDebito + 1];
        for (int i = 0; i <= quantidadeParcelasEmDebito; i++) {
            quantidadeEmDebito[i] = i;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.spinner_item, quantidadeEmDebito);

        spinnerPagarParcelas.setAdapter(adapter);
    }

    private void popularCliente() {
        ClienteDAO clienteDAO = new ClienteDAO(this);

        Cliente cliente = clienteDAO.findById(venda.getIdCliente());
        venda.setClienteVenda(cliente);
    }

    private void popularParcelas() {

        ParcelaDAO parcelaDAO = new ParcelaDAO(this);

        venda.setParcelas(parcelaDAO.buscarPorVenda(venda.getId()));

    }

    private void preencherCampos() {
        campoDataVenda.setText("Data da venda: " + venda.getDataVenda());

        nomeCliente.setText(venda.getClienteVenda().getNome());
        cpfCliente.setText("CPF:  " + venda.getClienteVenda().getCPF());
        telefoneCliente.setText("Telefone:  " + venda.getClienteVenda().getTelefone());


        valorDivida.setText(MoneyHelper.formatarEmReal(venda.calcularTotalDevedor()));
        valorVenda.setText(MoneyHelper.formatarEmReal(venda.calcularValorTotal()));
    }

    private void bind() {
        campoDataVenda = findViewById(R.id.campoDataVenda);
        telefoneCliente = findViewById(R.id.telefoneCliente);
        nomeCliente = findViewById(R.id.nomeCliente);
        cpfCliente = findViewById(R.id.cpfCliente);
        spinnerPagarParcelas = findViewById(R.id.spinnerPagarParcelas);
        valorVenda = findViewById(R.id.valorVenda);
        valorDivida = findViewById(R.id.valorDivida);
        buttonSave = findViewById(R.id.buttonSave);

    }
}
