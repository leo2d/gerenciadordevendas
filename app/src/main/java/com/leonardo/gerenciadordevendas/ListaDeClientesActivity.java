package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeClientesAdapter;
import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;

import static com.leonardo.gerenciadordevendas.ConstantesActivity.CHAVE_CLIENTE;

import java.util.List;

public class ListaDeClientesActivity extends AppCompatActivity {

    private static final String TELA_LISTA_CLIENTES = "Lista de Clientes";

    ListView lista_de_clientes;
    List<Cliente> clientes;
    final int TELA_CADASTRO_VENDAS = 1;
    private ListaDeClientesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_clientes);
        String title = 1 == 1 ? TELA_LISTA_CLIENTES : "Selecione o cliente";

        setTitle(title);

        binding();
        preencheLista();

        lista_de_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vaiParaTeladeVendas = new Intent(getApplicationContext(), TelaCadastroDeVendasActivity.class);
                vaiParaTeladeVendas.putExtra(CHAVE_CLIENTE, clientes.get(position));
                startActivityForResult(vaiParaTeladeVendas, TELA_CADASTRO_VENDAS);
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
        if (itemId == R.id.btn_voltar_menu_lista_produto) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void binding() {
        lista_de_clientes = findViewById(R.id.listaDeClientes);
    }

    private void preencheLista() {

        ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());

        clienteDAO.open();
        int i = 0;
        clientes = clienteDAO.findAll();

        String[] clits = new String[clientes.size()];
        for (Cliente cliente : clientes) {
            clits[i] = cliente.getNome();
            i++;
        }

        clienteDAO.close();

        adapter = new ListaDeClientesAdapter(clientes, this);

        //MANEIRA COM O ADAPTER
        lista_de_clientes.setAdapter(adapter);

        //MUITO IMPORTANTE
        //Maneira mais adequeda para mostrar dados na View
//        lista_de_usuarios.setAdapter(new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, users));
    }
}
