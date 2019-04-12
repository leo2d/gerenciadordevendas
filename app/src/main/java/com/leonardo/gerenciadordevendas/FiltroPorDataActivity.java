package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeVendaAdapter;
import com.leonardo.gerenciadordevendas.DAO.VendaDAO;
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

public class FiltroPorDataActivity extends AppCompatActivity {

    public static final String FILTRO_DATA = "Filtro por Data";
    List<Venda> vendas;
    EditText campoFiltroData;
    ListView filtroListaData;
    ListaDeVendaAdapter listaDeVendaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_por_data);
        setTitle(FILTRO_DATA);

        binding();
        campoFiltroData.setText("");

        campoFiltroData.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                preencherFiltroPorData();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_btn_voltar_telas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.btn_voltar_menu_lista_produto){
           finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencherFiltroPorData() {
        if (campoFiltroData.getText().toString().isEmpty()){
            vendas = new VendaDAO(getApplicationContext()).findAll();
        } else {
            vendas = new VendaDAO(getApplicationContext()).buscarPelaData(campoFiltroData.getText().toString());
        }

        String[] arrayString = new String[vendas.size()];
        for (int i = 0; i <  vendas.size(); i++) {
            Venda venda = vendas.get(i);
            arrayString[i] = venda.getDataVenda();
        }

        listaDeVendaAdapter = new ListaDeVendaAdapter(vendas, this);
        filtroListaData.setAdapter(listaDeVendaAdapter);
    }

    private void binding() {
        campoFiltroData = findViewById(R.id.campoFiltroData);
        filtroListaData = findViewById(R.id.filtroListaPesquisa);
    }
}
