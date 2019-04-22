package com.leonardo.gerenciadordevendas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leonardo.gerenciadordevendas.Helpers.MoneyHelper;
import com.leonardo.gerenciadordevendas.R;
import com.leonardo.gerenciadordevendas.entities.ItemVenda;
import com.leonardo.gerenciadordevendas.entities.Produto;

import java.util.List;

public class ListaItemVendaAdapter extends BaseAdapter {

    private List<ItemVenda> itens;
    private Context context;

    public ListaItemVendaAdapter(List<ItemVenda> itens, Context context) {
        this.itens = itens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItens = LayoutInflater
                .from(context)
                .inflate(R.layout.item_item_venda_lista, viewGroup, false);

        ItemVenda item = itens.get(i);

        TextView titulo = viewItens.findViewById(R.id.nomeProduto);
        titulo.setText(item.getProduto().getTitulo());

        TextView valorUn = viewItens.findViewById(R.id.valorUnitario);
        valorUn.setText(MoneyHelper.formatarEmReal(item.getProduto().getPreco()));

        TextView quantidade = viewItens.findViewById(R.id.quantidade);
        quantidade.setText(item.getQuantidade() + "");


        return viewItens;
    }
}
