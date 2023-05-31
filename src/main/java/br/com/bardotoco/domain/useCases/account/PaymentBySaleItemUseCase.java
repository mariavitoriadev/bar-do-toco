package main.java.br.com.bardotoco.domain.useCases.account;

import main.java.br.com.bardotoco.domain.entities.account.Account;
import main.java.br.com.bardotoco.domain.entities.cashier.Cashier;
import main.java.br.com.bardotoco.domain.entities.cashier.CashierStatus;
import main.java.br.com.bardotoco.domain.entities.saleitem.SaleItem;
import main.java.br.com.bardotoco.domain.useCases.saleItem.SaleItemDAO;
import main.java.br.com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import main.java.br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

public class PaymentBySaleItemUseCase {
    private AccountDAO accountDAO;
    private SaleItemDAO saleItemDAO;
    private Cashier cashier = Cashier.getInstance();

    public PaymentBySaleItemUseCase(AccountDAO accountDAO, SaleItemDAO saleItemDAO) {
        this.accountDAO = accountDAO;
        this.saleItemDAO = saleItemDAO;
    }

    public boolean payBySaleItem(SaleItem saleItem, double quantity) {
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        Account account = saleItem.getAccount();
        if(account == null ||  accountDAO.findOne(saleItem.getAccount().getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(saleItem == null ||  saleItemDAO.findOne(saleItem.getId()).isEmpty())
            throw new EntityNotFoundException("Item de Venda não encontrado.");

        if(account.getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        if(quantity <= 0)
            throw new IllegalArgumentException("Quantidade inválida.");

        double paymentAmount = saleItem.getProduct().getPrice() * quantity;
        saleItem.setPaidAmount(paymentAmount);

        account.updatePaidBySaleItemAmount(paymentAmount);

        return saleItemDAO.update(saleItem);

    }
}


