package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;

public class Produto extends BaseEntity implements Serializable {

    private String titulo;
    private String descricao;
    private double preco;
    private int idCategoria;
    private Categoria produtoCategoria;

    public Produto() {
    }

    public Produto(String titulo, String descricao, double preco, int idCategoria, Categoria produtoCategoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.idCategoria = idCategoria;
        this.produtoCategoria = produtoCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(Categoria produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
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
