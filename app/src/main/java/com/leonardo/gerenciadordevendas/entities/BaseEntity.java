package com.leonardo.gerenciadordevendas.entities;

import java.io.Serializable;

public class BaseEntity  implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
