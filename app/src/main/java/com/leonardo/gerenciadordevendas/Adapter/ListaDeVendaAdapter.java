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
import com.leonardo.gerenciadordevendas.entities.Venda;

import java.util.List;

public class ListaDeVendaAdapter extends BaseAdapter {

    private List<Venda> vendas;
    private Context context;

    public ListaDeVendaAdapter(List<Venda> vendas, Context context) {
        this.vendas = vendas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return vendas.size();
    }

    @Override
    public Object getItem(int position) {
        return vendas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vendas.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View viewVenda = LayoutInflater
                .from(context)
                .inflate(R.layout.adapter_lista_venda, viewGroup, false);
        Venda vendaDevolvida = vendas.get(position);


        TextView cliente = viewVenda.findViewById(R.id.item_venda_cliente);
        final String clienteNome = vendaDevolvida.getClienteVenda().getNome();
        cliente.setText(clienteNome);

        TextView quantParcelasPagas = viewVenda.findViewById(R.id.quantidadeParcelasPagas);
        quantParcelasPagas.setText(String.valueOf(vendaDevolvida.getParcelasPagas().size()));

        TextView quantParcela = viewVenda.findViewById(R.id.item_venda_parcelas);
        quantParcela.setText(String.valueOf(vendaDevolvida.getQuantidadeParcelas()));

        TextView data = viewVenda.findViewById(R.id.item_venda_data);
        data.setText(vendaDevolvida.getDataVenda());

        int img1 = 0;
        img1 = Imagem(vendaDevolvida.getId(), img1);

        ImageView img;
        img = viewVenda.findViewById(R.id.imgVenda);
        img.setImageResource(img1);

        return viewVenda;
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
