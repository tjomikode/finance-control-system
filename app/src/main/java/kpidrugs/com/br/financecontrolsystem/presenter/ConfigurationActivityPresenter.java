package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.util.ArrayList;

import kpidrugs.com.br.financecontrolsystem.dao.AccountDao;
import kpidrugs.com.br.financecontrolsystem.dao.CardDao;
import kpidrugs.com.br.financecontrolsystem.dao.ConfigurationAccountDao;
import kpidrugs.com.br.financecontrolsystem.dao.ConfigurationCardDao;
import kpidrugs.com.br.financecontrolsystem.model.Account;
import kpidrugs.com.br.financecontrolsystem.model.Card;
import kpidrugs.com.br.financecontrolsystem.model.ConfigurationAccount;
import kpidrugs.com.br.financecontrolsystem.model.ConfigurationCard;

public class ConfigurationActivityPresenter {
    ConfigurationInterface mView;
    Context c;

    public ConfigurationActivityPresenter(ConfigurationInterface view, Context context) {
        mView = view;
        c = context;
    }

    public ConfigurationAccount getConfAccount(String bankName) {
        ConfigurationAccountDao confAccount = new ConfigurationAccountDao(c);
        return confAccount.selectOnceConfigurationAccount(bankName);
    }

    public ConfigurationCard getConfCard(String cardName) {
        ConfigurationCardDao confCard = new ConfigurationCardDao(c);
        return confCard.selectOnceConfigurationCard(cardName);
    }

    public ArrayList<String> getAllAccountName() {
        AccountDao account = new AccountDao(c);;
        ArrayList<Account> ac = account.selectAccount();

        ArrayList<String> arrayAccount = new ArrayList<>();

        for ( Account a: ac ) {
            arrayAccount.add( a.getBankName() );
        }

        return arrayAccount;
    }

    public ArrayList<String> getAllCardName() {
        CardDao card = new CardDao(c);
        ArrayList<Card> ca =card.selectCard();
        ArrayList<String> arrayCard = new ArrayList<>();

        for ( Card a: ca ) {
            arrayCard.add( a.getCardName() );
        }

        return arrayCard;
    }

    public void registrationConfigurationAccount() {
        ConfigurationAccountDao confAccount = new ConfigurationAccountDao(c);
        String account = mView.getAccountSpinner();
        String renewalBalance = mView.getRenewalBalance();
        String balance = mView.getBalance();

        if ( !account.equals("") && !renewalBalance.equals("") && !balance.equals("") ) {
            boolean result = confAccount.updateConfigurationAccount(new ConfigurationAccount(account, Double.parseDouble(balance), renewalBalance ));

            if (result) {
                mView.updatedSuccessfully();
            } else {
                mView.databaseInsertError();
            }
        }else {
            mView.registrationError();
        }
    }

    public void registrationConfigurationCard() {
        ConfigurationCardDao confCard = new ConfigurationCardDao(c);
        String card = mView.getCardSpinner();
        String renewalCredit = mView.getRenewalCredit();
        String credit = mView.getCredit();

        if( !card.equals("") && !renewalCredit.equals("") && !credit.equals("") ) {
            boolean result = confCard.updateConfigurationCard(new ConfigurationCard(card, Double.parseDouble(credit), renewalCredit));

            if (result) {
                mView.updatedSuccessfully();
            } else {
                mView.databaseInsertError();
            }
        } else{
            mView.registrationError();
        }
    }
}
