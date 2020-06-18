package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.text.DecimalFormat;
import java.util.ArrayList;

import kpidrugs.com.br.financecontrolsystem.dao.AccountDao;
import kpidrugs.com.br.financecontrolsystem.dao.CardDao;
import kpidrugs.com.br.financecontrolsystem.model.Account;
import kpidrugs.com.br.financecontrolsystem.model.Card;

public class BalanceActivityPresenter {
    BalanceInterface mView;
    Context c;
    CardDao card;
    AccountDao account;
    DecimalFormat df;

    public BalanceActivityPresenter(BalanceInterface view, Context context) {
        mView = view;
        c = context;
        card = new CardDao(c);
        account = new AccountDao(c);
        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
    }

    public String getBalanceAccount(String bankName) {
        Account acc = this.account.selectOnceAccount(bankName);
        return String.valueOf( df.format(acc.getBalance() ) );
    }

    public String getCredit(String cardName) {
        Card ca = this.card.selectOnceCard(cardName);
        return String.valueOf( df.format(ca.getCredit() ) );
    }

    public ArrayList<String> getAllCardName() {
        ArrayList<Card> ca = card.selectCard();
        ArrayList<String> arrayCard = new ArrayList<>();

        for ( Card a: ca ) {
            arrayCard.add(a.getCardName());
        }

        return arrayCard;
    }

    public ArrayList<String> getAllAccountName() {
        ArrayList<Account> ac = account.selectAccount();
        ArrayList<String> arrayAccount = new ArrayList<>();

        for ( Account a: ac ) {
            arrayAccount.add( a.getBankName() );
        }

        return arrayAccount;
    }

}
