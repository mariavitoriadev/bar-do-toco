package br.com.bardotoco.model.entities;

public enum Status {

    OPENED("Aberto"),
    CLOSED("Fechado");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
