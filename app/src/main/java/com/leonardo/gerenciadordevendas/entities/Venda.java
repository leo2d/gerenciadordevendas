package com.leonardo.gerenciadordevendas.entities;

import java.util.List;

public class Venda extends BaseEntity {


    private int idVenda;
    private int idproduto;
    private int idCliente;
    private boolean isParcelado;
    private int quantidadeParcelas;

    private List<Parcela>  parcelas;
    private Produto produto;
    private Cliente cliente;

    public Venda() {
    }

    public Venda(int idVenda, int idproduto, int idCliente,
                 boolean isParcelado, int quantidadeParcelas, List<Parcela> parcelas,
                 Produto produto, Cliente cliente) {
        this.idVenda = idVenda;
        this.idproduto = idproduto;
        this.idCliente = idCliente;
        this.isParcelado = isParcelado;
        this.quantidadeParcelas = quantidadeParcelas;
        this.parcelas = parcelas;
        this.produto = produto;
        this.cliente = cliente;
    }

    public void gerarParcelas(int diaVencimento) {
        for (int i = 0; i < quantidadeParcelas; i++)
            this.parcelas.add(new Parcela(diaVencimento, calcularValorparcela(), this.getId()));
    }

    private double calcularValorparcela() {
        return produto.getPreco() / quantidadeParcelas;
    }

    public double  calcularTotalDevedor() {
        double resultado = 0;

            for (Parcela parcela : parcelas) {
                if(!parcela.isFoiPaga())
                    resultado += parcela.getValor();
            }

        return resultado;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean isParcelado() {
        return isParcelado;
    }

    public void setParcelado(boolean parcelado) {
        isParcelado = parcelado;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }
}
