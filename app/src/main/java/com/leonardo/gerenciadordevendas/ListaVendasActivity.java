package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeVendaAdapter;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.DAO.VendaDAO;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Produto;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_VENDA;

public class ListaVendasActivity extends AppCompatActivity {

    List<Venda> vendas;
    ListView listaDeVendas;
    ListaDeVendaAdapter listaDeVendaAdapter;

    public static final String VENDAS = "Vendas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vendas);
        setTitle(VENDAS);

        bind();
        popularVendas();

    }

    public void bind() {
        listaDeVendas = findViewById(R.id.listaDeVendas);
    }

    public void popularVendas() {
        try {
            VendaDAO vendaDAO = new VendaDAO(getApplicationContext());
            ParcelaDAO parcelaDAO = new ParcelaDAO(getApplicationContext());

            vendas = vendaDAO.findAll();

            for (Venda venda : vendas) {
                List<Parcela> parcelas = parcelaDAO.buscarPorVenda(venda.getId());
                venda.setParcelas(parcelas);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        listaDeVendaAdapter = new ListaDeVendaAdapter(vendas, this);
        listaDeVendas.setAdapter(listaDeVendaAdapter);

    }


}
