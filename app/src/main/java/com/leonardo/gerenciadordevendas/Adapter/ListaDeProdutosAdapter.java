package com.leonardo.gerenciadordevendas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

        int img1 = 0;
        img1 = Imagem(produtoDevolvido.getId(), img1);

        ImageView img;
        img = viewProduto.findViewById(R.id.imgProduto);
        img.setImageResource(img1);

        return viewProduto;
    }

    private int Imagem(int i, int img1) {
        switch (i) {
            case 1:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 2:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 3:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 4:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 5:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 6:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 7:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 8:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 9:
                img1 = R.drawable.ic_shopping_bag;
                break;
            case 10:
                img1 = R.drawable.ic_shopping_bag;
                break;
        }
        return img1;
    }
}
