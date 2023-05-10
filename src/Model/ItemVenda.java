package Model;
import java.security.Timestamp;
import java.util.*;

public class ItemVenda {
    private Timestamp id;
    private String nome;
    private int quantidade;
    private int valorPago;
    private int valor;

    public ItemVenda(Timestamp id, String nome, int quantidade, int valorPago, int valor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorPago = valorPago;
        this.valor = valor;
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

    public int getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
