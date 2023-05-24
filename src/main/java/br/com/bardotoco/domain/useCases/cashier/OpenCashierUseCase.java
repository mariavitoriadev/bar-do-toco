package br.com.bardotoco.domain.useCases.cashier;

import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.useCases.utils.CashierNotClosedException;

public class OpenCashierUseCase {

    private Cashier cashier = Cashier.getInstance();

    public Cashier openCashier() {

        if(cashier.getCashierStatus() == CashierStatus.OPENED)
            throw new CashierNotClosedException("Caixa já está aberto.");

        cashier.openCashier();

        return cashier;
    }
}
