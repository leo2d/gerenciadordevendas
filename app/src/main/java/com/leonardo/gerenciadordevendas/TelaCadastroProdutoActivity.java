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
    Spinner spinnerCategorias;
    List<Categoria> listaDeCategorias;
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

            categoria= (Categoria)spinnerCategorias.getSelectedItem();
            int idCategoriaSelecionada = categoria.getId();
            idCategoriaSelecionada = idCategoriaSelecionada == 0 ? 1 : idCategoriaSelecionada;
            categoria.setId(idCategoriaSelecionada);

            double campoPreco = Double.parseDouble(campo_preco_protuto.getText().toString());

            Produto  produto = new Produto(campo_titulo_produto.getText().toString(),
                    campo_descricao_produto.getText().toString(),
                    campoPreco, categoria.getId(), categoria);

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
        spinnerCategorias =  findViewById(R.id.spinnerCategoria);
    }

    public void preencherSpinner() {

        CategoriaDAO categoriaDAO = new CategoriaDAO(this.getApplicationContext());

        try {
            categoriaDAO.open();
            listaDeCategorias = categoriaDAO.findAll();

            ArrayAdapter<Categoria> adapter =
                    new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaDeCategorias);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerCategorias.setAdapter(adapter);
        } catch (Exception ex) {
            System.out.println("");
        } finally {
            categoriaDAO.close();
        }

    }
}
