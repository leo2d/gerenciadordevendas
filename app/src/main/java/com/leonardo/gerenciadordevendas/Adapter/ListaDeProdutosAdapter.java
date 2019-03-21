package com.leonardo.gerenciadordevendas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leonardo.gerenciadordevendas.R;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

public class ListaDeProdutosAdapter extends BaseAdapter {

    private List<Produto> produtos;
    private Context context;

    public ListaDeProdutosAdapter(List<Produto> produtos, Context context) {
        this.produtos = produtos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getIdProduto();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewProduto = LayoutInflater
                .from(context)
                .inflate(R.layout.item_produto_lista, viewGroup, false);
        Produto produtoDevolvido = produtos.get(position);

        TextView titulo = viewProduto.findViewById(R.id.item_produto_nome);
        titulo.setText(produtoDevolvido.getTitulo());

        TextView descricao = viewProduto.findViewById(R.id.item_produto_descricao);
        descricao.setText(produtoDevolvido.getDescricao());

        TextView preco = viewProduto.findViewById(R.id.item_produto_valor);
        preco.setText(String.valueOf(produtoDevolvido.getPreco()));


        return viewProduto;
    }
}
