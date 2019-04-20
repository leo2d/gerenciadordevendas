package com.leonardo.gerenciadordevendas.Enums;

public enum TelaDeOrigemEnum {
    MenuPrincipal(1),
    MenuClientes(2),
    MenuProdutos(3),
    MenuVendas(4),

    ListaClientes(5);

    int value;

    TelaDeOrigemEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
