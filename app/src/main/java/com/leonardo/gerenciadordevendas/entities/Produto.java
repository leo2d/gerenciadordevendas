package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;

public class Produto extends BaseEntity implements Serializable {

    private int idProduto;
    private String titulo;
    private String descricao;
    private double preco;
    private Categoria produtoCategoria;

    public Produto() {
    }

    public Produto(int idProduto, String titulo, String descricao, double preco, Categoria produtoCategoria) {
        this.idProduto = idProduto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.produtoCategoria = produtoCategoria;
    }

    public Categoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(Categoria produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
