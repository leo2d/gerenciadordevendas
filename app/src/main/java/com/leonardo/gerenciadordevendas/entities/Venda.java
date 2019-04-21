package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venda extends BaseEntity  implements Serializable {

    private String dataVenda;
    private boolean isParcelado;
    private int quantidadeParcelas;
    private int idCliente;
    private Cliente clienteVenda;
    private List<Parcela>  parcelas;
    private List<ItemVenda> itens;

    public Venda() {
        this.parcelas = new ArrayList<>();
        this.itens = new ArrayList<>();
    }

    public void gerarParcelas(int diaVencimento) {
        for (int i = 0; i < quantidadeParcelas; i++)
            this.parcelas.add(new Parcela(diaVencimento, calcularValorparcela(), this.getId()));
    }

    private double calcularValorparcela()
    {
        double valorTotal = 0;

        for (ItemVenda itemVenda: itens) {
            valorTotal += itemVenda.calcularValor();
        }

        return valorTotal / quantidadeParcelas;
    }

    public double  calcularTotalDevedor() {
        double resultado = 0;

            for (Parcela parcela : parcelas) {
                if(!parcela.isFoiPaga())
                    resultado += parcela.getValor();
            }

        return resultado;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public void adicionarItemVenda(ItemVenda itemVenda){
        this.itens.add(itemVenda);
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

    public List<Parcela> getParcelasPagas() {
        List<Parcela> parcelasPagas = new ArrayList<>();

        for (Parcela p: parcelas) {
            if(p.isFoiPaga()){
                parcelasPagas.add(p);
            }
        }

        return parcelasPagas;
    }
    public List<Parcela> getParcelasEmDebito() {
        List<Parcela> parcelasEmDebito = new ArrayList<>();

        for (Parcela p: parcelas) {
            if(!p.isFoiPaga()){
                parcelasEmDebito.add(p);
            }
        }

        return parcelasEmDebito;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Cliente getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(Cliente clienteVenda) {
        this.clienteVenda = clienteVenda;
    }
}
