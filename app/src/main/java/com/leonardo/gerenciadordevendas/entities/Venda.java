package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venda extends BaseEntity  implements Serializable {

    private String dataVenda;
    private boolean isParcelado;
    private int quantidadeParcelas;
    private int idProduto;
    private int idCliente;
    private Produto produtoVenda;
    private Cliente clienteVenda;
    private List<Parcela>  parcelas;

    public Venda() {
        this.parcelas = new ArrayList<Parcela>();
    }

    public void gerarParcelas(int diaVencimento) {
        for (int i = 0; i < quantidadeParcelas; i++)
            this.parcelas.add(new Parcela(diaVencimento, calcularValorparcela(), this.getId()));
    }

    public void gerarParcelas(int diaVencimento, double valorProduto) {
        for (int i = 0; i < quantidadeParcelas; i++)
            this.parcelas.add(new Parcela(diaVencimento, calcularValorparcela(valorProduto), this.getId()));
    }

    private double calcularValorparcela() {
        return produtoVenda.getPreco() / quantidadeParcelas;
    }

    private double calcularValorparcela(double valorProduto) {
        return valorProduto / quantidadeParcelas;
    }

    public double  calcularTotalDevedor() {
        double resultado = 0;

            for (Parcela parcela : parcelas) {
                if(!parcela.isFoiPaga())
                    resultado += parcela.getValor();
            }

        return resultado;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
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
