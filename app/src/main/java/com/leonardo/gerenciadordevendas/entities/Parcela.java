package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;

public class Parcela extends BaseEntity implements Serializable {

    private boolean foiPaga;
    private int indicadorPagamento;
    private String diaVencimento;
    private double valor;
    private Venda parcelaVenda;
    private int idVenda;

    public Parcela() {
    }


    public Parcela(int diaVencimento, double valorParcela, int idVenda) {
        this.idVenda = idVenda;
        this.diaVencimento = diaVencimento + "";
        this.valor = valorParcela;
    }

    public int getIndicadorPagamento() {
        return indicadorPagamento;
    }

    public void setIndicadorPagamento(int indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
        this.setFoiPaga(indicadorPagamento == 0 ? false : true);
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(String diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Venda getParcelaVenda() {
        return parcelaVenda;
    }

    public void setParcelaVenda(Venda parcelaVenda) {
        this.parcelaVenda = parcelaVenda;
    }


    public boolean isFoiPaga() {
        return indicadorPagamento == 1;
    }

    public void setFoiPaga(boolean foiPaga) {
        this.foiPaga = foiPaga;
        this.indicadorPagamento = foiPaga ? 1 : 0;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}
