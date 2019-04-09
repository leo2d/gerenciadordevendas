package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends BaseEntity implements Serializable {

    private String nome;
    private String telefone;
    private String CPF;
    private String RG;
    private String email;
    private List<Venda> vendas;

    public Cliente() {
    }

    public Cliente(String nome, String telefone, String CPF, String RG, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
        this.RG = RG;
        this.email = email;
        this.vendas = new ArrayList<Venda>();
    }

    public double calcularTotalDevedor() {
        double resultado = 0;

        for (Venda venda : vendas) {
            resultado += venda.calcularTotalDevedor();
        }

        return resultado;
    }

    public double calcularTotalDevedorPorVenda(int idVenda) {
        double resultado = 0;

        for (Venda venda : vendas) {
            if (venda.getId() != idVenda)
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
