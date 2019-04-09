package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;

public class Categoria extends BaseEntity {

    private String nome;

    public Categoria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
