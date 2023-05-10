package Model;

public enum Status {

    ABERTO("aberto"),
    FECHADO("fechado");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
