package com.leonardo.gerenciadordevendas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        return viewCliente;
    }
}
