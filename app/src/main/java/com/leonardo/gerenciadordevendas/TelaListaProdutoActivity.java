package com.leonardo.gerenciadordevendas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeProdutosAdapter;
import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

public class TelaListaProdutoActivity extends AppCompatActivity {

    private static final String TELA_LISTA_PRODUTOS = "Lista de Produtos";

    Button btn_voltar_lista_protutos;
    ListView lista_de_produtos;
    List<Produto> produtos;
    ListaDeProdutosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_produto);

        setTitle(TELA_LISTA_PRODUTOS);
        binding();
        preencheLista();

        btn_voltar_lista_protutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void binding() {
        btn_voltar_lista_protutos = findViewById(R.id.btnVoltarListaProduto);
        lista_de_produtos = findViewById(R.id.listaDeProdutos);
    }

    private void preencheLista() {

        ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());

        produtoDAO.open();
        int i = 0;
        produtos = produtoDAO.findAll();

        String[] prods = new String[produtos.size()];
        for (Produto produto : produtos) {
            prods[i] = produto.getTitulo();
            i++;
        }

        produtoDAO.close();

        adapter = new ListaDeProdutosAdapter(produtos, this);

        lista_de_produtos.setAdapter(adapter);

    }
}
