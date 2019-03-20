package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

public class TelaListaProdutoActivity extends AppCompatActivity {

    private static final String TELA_LISTA_PRODUTOS = "Lista de Produtos";

    Button btn_voltar_lista_protutos;
    ListView lista_de_produtos;
    List<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_produto);

        setTitle(TELA_LISTA_PRODUTOS);
        binding();
    }

    private void binding() {
        btn_voltar_lista_protutos =  findViewById(R.id.btnVoltarListaProduto);
        lista_de_produtos =  findViewById(R.id.listaDeProdutos);
    }

    private void preencheLista(){
//
//        ClienteDAO clienteDAO = new ClienteDAO(getApplicationContext());
//
//        clienteDAO.open();
//        int i = 0;
//        clientes = clienteDAO.findAll();
//
//        String[] clits = new String[clientes.size()];
//        for (Cliente cliente : clientes) {
//            clits[i] = cliente.getNome();
//            i++;
//        }
//
//        clienteDAO.close();
//
//        adapter = new ListaDeClientesAdapter(clientes, this);
//
//        //MANEIRA COM O ADAPTER
//        lista_de_clientes.setAdapter(adapter);
//
//        //MUITO IMPORTANTE
//        //Maneira mais adequeda para mostrar dados na View
////        lista_de_usuarios.setAdapter(new ArrayAdapter<>(this,
////                android.R.layout.simple_list_item_1, users));
    }
}
