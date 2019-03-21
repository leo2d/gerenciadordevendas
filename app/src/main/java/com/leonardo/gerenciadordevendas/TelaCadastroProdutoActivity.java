package com.leonardo.gerenciadordevendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.leonardo.gerenciadordevendas.DAO.CategoriaDAO;
import com.leonardo.gerenciadordevendas.DAO.ClienteDAO;
import com.leonardo.gerenciadordevendas.DAO.ProdutoDAO;
import com.leonardo.gerenciadordevendas.entities.Categoria;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

public class TelaCadastroProdutoActivity extends AppCompatActivity {

    private static final String TELA_CADASTRO_PRODUTO = "Cadastro de Produtos";


    EditText campo_titulo_produto,
            campo_descricao_produto,
            campo_preco_protuto;
    Spinner spinner_categoria_produto;
    List<Categoria> lista_categorias;
    Produto produto;
    Produto idProduto;
    Categoria categoria;
    Categoria idCategoria;
    final int TELA_PRODUTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_produto);
        setTitle(TELA_CADASTRO_PRODUTO);


        binding();
        preencherSpinner();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ite_menu_cadastro_produto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.btn_menu_cadastrar_produto) {


            categoria = new Categoria();
            categoria.setIdCategoria(spinner_categoria_produto.getSelectedItemPosition());

            double campoPreco = Double.parseDouble(campo_preco_protuto.getText().toString());

            Produto  produto = new Produto(1, campo_titulo_produto.getText().toString(),
                    campo_descricao_produto.getText().toString(),
                    campoPreco, categoria);

            ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());
            produtoDAO.open();
            produtoDAO.gravarProduto(produto);
            produtoDAO.close();
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void binding() {
        campo_titulo_produto =  findViewById(R.id.campoTituloProdutoCadastro);
        campo_descricao_produto =  findViewById(R.id.campoDescricaoProdutoCadastro);
        campo_preco_protuto =  findViewById(R.id.campoPrecoProdutoCadastro);
        spinner_categoria_produto =  findViewById(R.id.spinnerCategoria);
    }

    public void preencherSpinner() {

        CategoriaDAO categoriaDAO = new CategoriaDAO(getApplicationContext());
        categoriaDAO.open();

        int idx = 0;
        lista_categorias = categoriaDAO.findAll();
        String[] categorias = new String[lista_categorias.size()];
        for (Categoria c: lista_categorias){
            categorias[idx] = c.getNome();
            idx++;
        }
        categoriaDAO.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_spinner, categorias);
        spinner_categoria_produto.setAdapter(adapter);

    }
}
