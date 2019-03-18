package com.leonardo.gerenciadordevendas.entities;

public class Parcela extends BaseEntity {

    private int idParcela;
    private boolean foiPaga;
    private int diaVencimento;
    private double valor;

    private int idVenda;
    private Venda venda;

    public Parcela(int diaVencimento, double valor, int idVenda) {
        this.diaVencimento = diaVencimento;
        this.valor = valor;
        this.idVenda = idVenda;
        this.foiPaga = false;
    }

    public Parcela() {
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public boolean isFoiPaga() {
        return foiPaga;
    }

    public void setFoiPaga(boolean foiPaga) {
        this.foiPaga = foiPaga;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
}
