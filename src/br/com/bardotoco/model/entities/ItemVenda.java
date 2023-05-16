package br.com.bardotoco.model.entities;
import java.sql.Timestamp;

public class ItemVenda {
    private Timestamp id;
    private String nome;
    private int quantidade;
    private float valorPago;
    private float valorTotal;
    private Conta conta;
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVenda(Timestamp id, String nome, int quantidade, float valorPago, float valorTotal, Conta conta, Produto produto) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorPago = valorPago;
        this.valorTotal = valorTotal;
        this.conta = conta;
        this.produto = produto;
    }

    public void adicionarPagamento (int quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Conta getConta() { return conta; }

    public void setConta(Conta conta) { this.conta = conta; }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) { this.produto = produto; }
}
