package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.util.ArrayList;

import kpidrugs.com.br.financecontrolsystem.dao.AccountDao;
import kpidrugs.com.br.financecontrolsystem.dao.CardDao;
import kpidrugs.com.br.financecontrolsystem.dao.ConfigurationCardDao;
import kpidrugs.com.br.financecontrolsystem.model.Account;

public class CardActivityPresenter {
    private CardInterface mView;
    private Context c;
    private boolean result1, result2;

    public CardActivityPresenter(CardInterface view, Context context) {
        mView = view;
        c = context;
    }

    public boolean cardRegistration() {
        String cardName = mView.getCardName();
        String credit = mView.getCredit();
        String bankName = mView.getBankName();

        if ( bankName.equals("") || credit.equals("") || cardName.equals("") ) {
            mView.registrationError();
            return false;
        } else {
            CardDao card = new CardDao(c);
            result1 = card.insertCard(cardName, Double.parseDouble(credit), bankName);

            if ( result1 )
                configurationCardRegistration();

            if ( result1 && result2 ) {
                mView.successfullyInserted();
                return true;
            } else {
                mView.databaseInsertError();
                return false;
            }
        }

    }

    public void configurationCardRegistration() {
        String cardName = mView.getCardName();
        String credit = mView.getCredit();
        String receiptDate = mView.getReceiptDate();

        if (receiptDate.equals("")) {
            mView.registrationError();
        } else {
            ConfigurationCardDao config = new ConfigurationCardDao(c);
            result2 = config.insertConfigurationCard(cardName, Double.parseDouble(credit), receiptDate);
        }
    }

    public ArrayList<String> getAllAccountName() {
        AccountDao account = new AccountDao(c);
        ArrayList<String> nameAccount = new ArrayList<>();

        for( Account ac: account.selectAccount() ) {
            nameAccount.add( ac.getBankName() );
        }

        return nameAccount;
    }
}
