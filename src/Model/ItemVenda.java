package Model;
import java.sql.Timestamp;

public class ItemVenda {
    private Timestamp id;
    private String nome;
    private int quantidade;
    private float valorPago;
    private float valor;

    public ItemVenda() {
    }

    public ItemVenda(Timestamp id, String nome, int quantidade, float valorPago, float valor) {
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

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
