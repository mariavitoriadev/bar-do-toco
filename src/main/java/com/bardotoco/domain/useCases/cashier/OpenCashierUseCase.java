package com.bardotoco.domain.useCases.cashier;

import com.bardotoco.domain.entities.cashier.Cashier;
import com.bardotoco.domain.entities.cashier.CashierStatus;
import com.bardotoco.domain.useCases.utils.CashierNotClosedException;

public class OpenCashierUseCase {

    private Cashier cashier = Cashier.getInstance();

    public Cashier openCashier() {

        if(cashier.isCashierOpened())
            throw new CashierNotClosedException("Caixa já está aberto.");

        cashier.openCashier();

        return cashier;
    }
}
