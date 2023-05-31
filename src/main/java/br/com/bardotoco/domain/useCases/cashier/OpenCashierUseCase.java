package main.java.br.com.bardotoco.domain.useCases.cashier;

import main.java.br.com.bardotoco.domain.entities.cashier.Cashier;
import main.java.br.com.bardotoco.domain.entities.cashier.CashierStatus;
import main.java.br.com.bardotoco.domain.useCases.utils.CashierNotClosedException;

public class OpenCashierUseCase {

    private Cashier cashier = Cashier.getInstance();

    public Cashier openCashier() {

        if(cashier.getCashierStatus() == CashierStatus.OPENED)
            throw new CashierNotClosedException("Caixa já está aberto.");

        cashier.openCashier();

        return cashier;
    }
}
