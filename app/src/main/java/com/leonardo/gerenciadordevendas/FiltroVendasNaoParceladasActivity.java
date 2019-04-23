package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeVendaAdapter;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.DAO.VendaDAO;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

public class FiltroVendasNaoParceladasActivity extends AppCompatActivity {

    public static final String VENDA_NAO_PARCELADA = "Vendas não Parceladas";
    List<Venda> vendas;
    ListView listaVendaNaoParcelada;
    ListaDeVendaAdapter listaDeVendaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_vendas_nao_parceladas);
        setTitle(VENDA_NAO_PARCELADA);
        binding();
        preencherLista();
    }

    private void binding() {
        listaVendaNaoParcelada = findViewById(R.id.filtroListaNaoParcelada);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_btn_voltar_telas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.btn_voltar_menu_lista_produto) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void preencherLista() {

        try {
            VendaDAO vendaDAO = new VendaDAO(getApplicationContext());
            ParcelaDAO parcelaDAO = new ParcelaDAO(getApplicationContext());

            vendas = vendaDAO.buscarVendaNaoParcelada();

            for (Venda venda : vendas) {
                List<Parcela> parcelas = parcelaDAO.buscarPorVenda(venda.getId());
                venda.setParcelas(parcelas);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        listaDeVendaAdapter = new ListaDeVendaAdapter(vendas, this);
        listaVendaNaoParcelada.setAdapter(listaDeVendaAdapter);
    }


}
