package br.com.bardotoco.model.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conta {
    private int id;
    private LocalDateTime horarioAbertura;
    private LocalDateTime horarioFechamento;
    private double valorTotal;
    private double valorPago;
    private ArrayList<Timestamp> listaItensPagamento;
    private int valorPagamento;
    private Mesa mesa;

    public Conta(int id, LocalDateTime horarioAbertura, LocalDateTime horarioFechamento, double valorTotal, double valorPago) {
        this.id = id;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public ArrayList<Timestamp> getListaItensPagamento() {
        return listaItensPagamento;
    }

    public int getValorPagamento() {
        return valorPagamento;
    }

    public Mesa getMesa() { return mesa; }

    public void setId(int id) {
        this.id = id;
    }

    public void setHorarioAbertura(LocalDateTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public void setHorarioFechamento(LocalDateTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public void setListaItensPagamento(ArrayList<Timestamp> listaItensPagamento) {
        this.listaItensPagamento = listaItensPagamento;
    }

    public void setValorPagamento(int valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public void setMesa(Mesa mesa) { this.mesa = mesa;}

    /*===========================================================*/
    /*========================= Funções =========================*/
    /*===========================================================*/



}
