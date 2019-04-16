package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeVendaAdapter;
import com.leonardo.gerenciadordevendas.DAO.ParcelaDAO;
import com.leonardo.gerenciadordevendas.DAO.VendaDAO;
import com.leonardo.gerenciadordevendas.entities.Parcela;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

public class ListaVendaActivity extends AppCompatActivity {

    List<Venda> vendas;
    ListView listaDeVendas;
    ListaDeVendaAdapter listaDeVendaAdapter;

    public static final String VENDAS = "Vendas";
    final int TELA_DETALHE_VENDA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_venda);
        setTitle(VENDAS);

        bind();
        popularVendas();

        listaDeVendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalharVenda = new Intent(getApplicationContext(), DetalharVendaActivity.class);
                detalharVenda.putExtra("VENDA", vendas.get(position));
                startActivityForResult(detalharVenda, TELA_DETALHE_VENDA);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filtros, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_filtro_data){
            Intent intent = new Intent(getApplicationContext(), FiltroPorDataActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
