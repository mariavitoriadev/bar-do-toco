package br.com.bardotoco.model.entities;

public class Mesa {
    private int codigo;
    private Conta conta;

    public Mesa(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
        if(conta != null )
            this.conta.setMesa(this);
    }
}
