package com.leonardo.gerenciadordevendas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.leonardo.gerenciadordevendas.Adapter.ListaDeProdutosAdapter;
import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

public class TelaListaProdutoActivity extends AppCompatActivity {

    private static final String TELA_LISTA_PRODUTOS = "Lista de Produtos";

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
        lista_de_produtos = findViewById(R.id.listaDeProdutos);
    }

    private void preencheLista() {

        ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());

        produtoDAO.open();

        produtos = produtoDAO.findAll();


        produtoDAO.close();

        adapter = new ListaDeProdutosAdapter(produtos, this);

        lista_de_produtos.setAdapter(adapter);

    }
}
