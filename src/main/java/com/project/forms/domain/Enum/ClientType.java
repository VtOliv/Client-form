package com.project.forms.domain.Enum;

public enum ClientType {
    PF(1, "Pessoa Física"),
    PJ(2, "Pessoa Jurídica");

    private final int id;
    private final String descricao;

    ClientType(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ClientType fromId(int id) {
        for (ClientType type : ClientType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Id inválido para ClientType: " + id);
    }
}
