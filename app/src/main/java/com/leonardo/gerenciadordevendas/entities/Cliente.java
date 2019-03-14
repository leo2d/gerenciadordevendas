package com.leonardo.gerenciadordevendas.entities;

import java.util.List;

public class Cliente extends BaseEntity {
    private String nome;
    private List<Venda> vendas;

    public double  calcularTotalDevedor() {
         double resultado = 0;

        for (Venda venda : vendas) {
            resultado += venda.calcularTotalDevedor();
        }

        return resultado;
    }

    public double  calcularTotalDevedorPorVenda(int idVenda) {
        double resultado = 0;

        for (Venda venda : vendas) {
            if(venda.getId() != idVenda)
                continue;

            resultado += venda.calcularTotalDevedor();
        }

        return resultado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
