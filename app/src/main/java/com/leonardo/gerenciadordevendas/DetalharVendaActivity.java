package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;
import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_VENDA;

public class DetalharVendaActivity extends AppCompatActivity {

    public static final String VENDA_DETALHADA = "Venda Nro:  ";

    Venda venda;

    EditText campoDataVenda, cpfCliente, telefoneCliente, nomeCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_venda);

        venda = (Venda) getIntent().getSerializableExtra(CHAVE_VENDA);
        setTitle(VENDA_DETALHADA + venda.getId());

        popularCliente();
        popularParcelas();

        bind();
        preencherCampos();
    }

    private void popularCliente() {
        ClienteDAO clienteDAO = new ClienteDAO(this);

        Cliente cliente = clienteDAO.findById(venda.getIdCliente());
        venda.setClienteVenda(cliente);
    }

    private void popularParcelas() {

        ParcelaDAO parcelaDAO = new ParcelaDAO(this);

        venda.setParcelas(parcelaDAO.buscarPorVenda(venda.getId()));

        int quantidadeParcelasEmDebito = venda.getParcelasEmDebito().size();

        int[] quantidadeEmDebito = new int[quantidadeParcelasEmDebito];
        for (int i = 1; i <= quantidadeParcelasEmDebito; i++) {
            quantidadeEmDebito[i - 1] = i;
        }
    }

    private void preencherCampos() {
        campoDataVenda.setText("Data da venda: " + venda.getDataVenda());

        nomeCliente.setText(venda.getClienteVenda().getNome());
        cpfCliente.setText("CPF:  " + venda.getClienteVenda().getCPF());
        telefoneCliente.setText("Telefone:  " + venda.getClienteVenda().getTelefone());
    }

    private void bind() {
        campoDataVenda = findViewById(R.id.campoDataVenda);
        telefoneCliente = findViewById(R.id.telefoneCliente);
        nomeCliente = findViewById(R.id.nomeCliente);
        cpfCliente = findViewById(R.id.cpfCliente);
    }
}
