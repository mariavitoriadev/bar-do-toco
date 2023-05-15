package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Conta {
    private int id;
    private LocalDateTime horarioAbertura;
    private LocalDateTime horarioFechamento;
    private int valorTotal;
    private int valorPago;
    private ArrayList<Timestamp> listaItensPagamento;
    private int valorPagamento;

    public Conta(int id, LocalDateTime horarioAbertura, LocalDateTime horarioFechamento, int valorTotal, int valorPago, ArrayList<Timestamp> listaItensPagamento, int valorPagamento) {
        this.id = id;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.listaItensPagamento = listaItensPagamento;
        this.valorPagamento = valorPagamento;
    }

    public Conta(int id, LocalDateTime horarioAbertura, LocalDateTime horarioFechamento, int valorTotal, int valorPago, int valorPagamento) {
        this.id = id;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.valorPagamento = valorPagamento;
    }

    /*============================================================*/
    /*====================== Gets & Setters ======================*/
    /*============================================================*/

    public int getId() {
        return id;
    }

    public LocalDateTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public LocalDateTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public int getValorPago() {
        return valorPago;
    }

    public ArrayList<Timestamp> getListaItensPagamento() {
        return listaItensPagamento;
    }

    public int getValorPagamento() {
        return valorPagamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHorarioAbertura(LocalDateTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public void setHorarioFechamento(LocalDateTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public void setListaItensPagamento(ArrayList<Timestamp> listaItensPagamento) {
        this.listaItensPagamento = listaItensPagamento;
    }

    public void setValorPagamento(int valorPagamento) {
        this.valorPagamento = valorPagamento;
    }


    /*===========================================================*/
    /*========================= Funções =========================*/
    /*===========================================================*/

    public ItemVenda criarItemVenda(Produto produto, int quantidade){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        return new ItemVenda(timestamp, produto.getNome(), quantidade, 0.0f, (float)produto.getValor());
    }

}
