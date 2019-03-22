package com.leonardo.gerenciadordevendas.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leonardo.gerenciadordevendas.R;
import com.leonardo.gerenciadordevendas.entities.Cliente;
import java.util.List;


public class ListaDeClientesAdapter extends BaseAdapter {

    private List<Cliente> clientes;
    private Context context;

    public ListaDeClientesAdapter(List<Cliente> clientes, Context context) {
        this.clientes = clientes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return clientes.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View viewCliente = LayoutInflater
                .from(context)
                .inflate(R.layout.item_cliente_lista, viewGroup, false);
        Cliente clienteDevolvido = clientes.get(position);

        TextView nome = viewCliente.findViewById(R.id.item_cliente_nome);
        nome.setText(clienteDevolvido.getNome());

        TextView sobrenome = viewCliente.findViewById(R.id.item_cliente_email);
        sobrenome.setText(clienteDevolvido.getEmail());

        int img1 = 0;
        img1 = Imagem(clienteDevolvido.getId(), img1);

        ImageView img;
        img = viewCliente.findViewById(R.id.imgCliente);
        img.setImageResource(img1);



        return viewCliente;
    }

    private int Imagem(int i, int img1)
    {
        switch (i){
            case 1:
                img1 = R.drawable.ic_team;
                break;
            case 2:
                img1 = R.drawable.ic_collaboration;
                break;
            case 3:
                img1 = R.drawable.ic_group;
                break;

            case 4:
                img1 = R.drawable.ic_networking;
                break;

            case 5:
                img1 = R.drawable.ic_friendship;
                break;

        }
        return img1;
    }
}
