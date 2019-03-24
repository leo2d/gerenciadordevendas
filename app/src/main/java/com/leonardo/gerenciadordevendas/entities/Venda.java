package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;
import java.util.List;

public class Venda extends BaseEntity  implements Serializable {

    private int idVenda;
    private String dataVenda;
    private boolean isParcelado;
    private int quantidadeParcelas;
    private List<Parcela>  parcelas;
    private Produto produtoVenda;
    private Cliente clienteVenda;

    public Venda() {
    }

    public Venda(int idVenda, String dataVenda, boolean isParcelado, int quantidadeParcelas, List<Parcela> parcelas, Produto produtoVenda, Cliente clienteVenda) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.isParcelado = isParcelado;
        this.quantidadeParcelas = quantidadeParcelas;
        this.parcelas = parcelas;
        this.produtoVenda = produtoVenda;
        this.clienteVenda = clienteVenda;
    }

    public void gerarParcelas(int diaVencimento) {
        for (int i = 0; i < quantidadeParcelas; i++)
            this.parcelas.add(new Parcela(diaVencimento, calcularValorparcela(), this.getId()));
    }

    private double calcularValorparcela() {
        return produtoVenda.getPreco() / quantidadeParcelas;
    }

    public double  calcularTotalDevedor() {
        double resultado = 0;

            for (Parcela parcela : parcelas) {
                if(!parcela.isFoiPaga())
                    resultado += parcela.getValor();
            }

        return resultado;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
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

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Produto getProdutoVenda() {
        return produtoVenda;
    }

    public void setProdutoVenda(Produto produtoVenda) {
        this.produtoVenda = produtoVenda;
    }

    public Cliente getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(Cliente clienteVenda) {
        this.clienteVenda = clienteVenda;
    }
}
