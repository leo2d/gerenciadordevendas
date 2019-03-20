package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;

public class Parcela extends BaseEntity implements Serializable {

    private int idParcela;
    private boolean foiPaga;
    private String diaVencimento;
    private double valor;
    private Venda parcelaVenda;

    public Parcela() {
    }

    public Parcela(int idParcela, boolean foiPaga, String diaVencimento, double valor, Venda parcelaVenda) {
        this.idParcela = idParcela;
        this.foiPaga = foiPaga;
        this.diaVencimento = diaVencimento;
        this.valor = valor;
        this.parcelaVenda = parcelaVenda;
    }

    public Parcela(int diaVencimento, double calcularValorparcela, int id) {
        super();
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

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }


    public boolean isFoiPaga() {
        return foiPaga;
    }

    public void setFoiPaga(boolean foiPaga) {
        this.foiPaga = foiPaga;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}
