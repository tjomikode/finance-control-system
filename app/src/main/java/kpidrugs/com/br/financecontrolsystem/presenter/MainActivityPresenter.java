package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kpidrugs.com.br.financecontrolsystem.dao.AccountDao;
import kpidrugs.com.br.financecontrolsystem.dao.CardDao;
import kpidrugs.com.br.financecontrolsystem.dao.ConfigurationAccountDao;
import kpidrugs.com.br.financecontrolsystem.dao.ConfigurationCardDao;
import kpidrugs.com.br.financecontrolsystem.dao.UserDao;
import kpidrugs.com.br.financecontrolsystem.model.Account;
import kpidrugs.com.br.financecontrolsystem.model.Card;
import kpidrugs.com.br.financecontrolsystem.model.ConfigurationAccount;
import kpidrugs.com.br.financecontrolsystem.model.ConfigurationCard;

public class MainActivityPresenter {
    MainInterface mView;
    Context c;
    Date date;
    int monthNow;
    int dayNow;
    boolean isFebruary, isThirtyOne;

    public MainActivityPresenter(MainInterface view, Context context) {
        mView = view;
        c = context;
        date = new Date(System.currentTimeMillis());
        monthNow = Integer.parseInt( new SimpleDateFormat("MM").format(date) );
        dayNow = Integer.parseInt( new SimpleDateFormat("dd").format(date) );

        isFebruary = isThirtyOne = false;

        if ( monthNow == 02 ) {
            isFebruary = true;
        } else if ( monthNow == 1 || monthNow == 3 || monthNow == 5 || monthNow == 7 || monthNow == 8 || monthNow == 10 || monthNow == 12 ) {
            isThirtyOne = true;
        }
    }

    public boolean existAccount() {
        AccountDao accountDao = new AccountDao(c);

        if ( accountDao.selectAccount().size() > 0 )
            return true;
        else
            return false;
    }

    public boolean existCard() {
        CardDao cardDao = new CardDao(c);

        if ( cardDao.selectCard().size() > 0 )
            return true;
        else
            return false;
    }

    public boolean existUser() {
        UserDao userDao = new UserDao(c);

        if( userDao.selectUser() != null )
            return true;
        else
            return false;
    }

    public void renewalBalanceAccount() {
        ConfigurationAccountDao config = new ConfigurationAccountDao(c);
        ArrayList<ConfigurationAccount> configAccount = config.selectConfigAccount();
        int day;

        for( ConfigurationAccount ca: configAccount ) {
            day = Integer.parseInt(ca.getReceiptDate());

            if ( (day == 29 || day == 30 || day == 31) && isFebruary ) {
                day = 28;
            } else if( day == 31 && !isThirtyOne ) {
                day = 30;
            }

            if ( day == dayNow ) {
                AccountDao acDao = new AccountDao(c);
                Account ac = (acDao.selectOnceAccount(ca.getBankName()));
                ac.setBalance( ac.getBalance() + ca.getBalance() );
                acDao.updateBalanceAccount(ac);
            }
        }
    }

    public void renewalCredit() {
        ConfigurationCardDao config = new ConfigurationCardDao(c);
        ArrayList<ConfigurationCard> configCard = config.selectConfigurationCard();
        int day;

        for( ConfigurationCard ca: configCard ) {
            day = Integer.parseInt(ca.getReceiptDate());

            if ( (day == 29 || day == 30 || day == 31) && isFebruary ) {
                day = 28;
            } else if( day == 31 && !isThirtyOne ) {
                day = 30;
            }

            if ( day == dayNow ) {
                CardDao caDao = new CardDao(c);
                Card card = (caDao.selectOnceCard(ca.getCardName()));
                card.setCredit( card.getCredit() + ca.getCredit() );
                caDao.updateCreditCard(card);
            }
        }
    }
}
