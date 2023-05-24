package br.com.bardotoco.domain.useCases.account;

import br.com.bardotoco.domain.entities.account.Account;
import br.com.bardotoco.domain.entities.cashier.Cashier;
import br.com.bardotoco.domain.entities.cashier.CashierStatus;
import br.com.bardotoco.domain.entities.saleitem.SaleItem;
import br.com.bardotoco.domain.useCases.saleItem.SaleItemDAO;
import br.com.bardotoco.domain.useCases.utils.CashierNotOpenedException;
import br.com.bardotoco.domain.useCases.utils.EntityNotFoundException;

import java.util.List;

public class PaymentByValueUseCase {

    private AccountDAO accountDAO;
    private SaleItemDAO saleItemDAO;
    private Cashier cashier = Cashier.getInstance();

    public PaymentByValueUseCase(AccountDAO accountDAO, SaleItemDAO saleItemDAO) {
        this.accountDAO = accountDAO;
        this.saleItemDAO = saleItemDAO;
    }

    public boolean payByValue(Account account, double paymentAmount) {
        if(cashier.getCashierStatus() == CashierStatus.CLOSED)
            throw new CashierNotOpenedException("Operação não permitida enquanto o caixa estiver fechado.");

        if(account == null ||  accountDAO.findOne(account.getId()).isEmpty())
            throw new EntityNotFoundException("Conta não encontrada.");

        if(account.getClosingTime() != null)
            throw new IllegalArgumentException("Conta já está fechada.");

        List<SaleItem> accountSaleItems = saleItemDAO.findByAccount(account);
        if(accountSaleItems.isEmpty())
            throw new EntityNotFoundException("Nenhum Item de Venda encontrado nessa conta.");

        account.updatePaidByValueAmount(paymentAmount);
        return accountDAO.update(account);
    }

}
