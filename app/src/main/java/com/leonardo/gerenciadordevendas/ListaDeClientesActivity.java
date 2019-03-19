package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeClientesAdapter;
import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;

import java.util.List;

public class ListaDeClientesActivity extends AppCompatActivity {

    private static final String TELA_LISTA_CLIENTES = "Lista de Clientes";

    Button btn_voltar;
    ListView lista_de_clientes;
    List<Cliente> clientes;
    final int TELA_PRODUTOS_CLIENTES = 1;
    private ListaDeClientesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_clientes);
        setTitle(TELA_LISTA_CLIENTES);

        binding();
        preencheLista();

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void binding() {
        btn_voltar = findViewById(R.id.btnVoltar);
        lista_de_clientes =  findViewById(R.id.listaDeClientes);
    }

    private void preencheLista(){

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
