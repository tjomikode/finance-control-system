package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kpidrugs.com.br.financecontrolsystem.dao.SpendingDao;
import kpidrugs.com.br.financecontrolsystem.model.Spending;

public class SpendingYearActivityPresenter {
    SpendingYearInterface mView;
    Context c;
    SpendingDao spendingDao;
    final int MESES_ANO = 12;

    public SpendingYearActivityPresenter(SpendingYearInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public Float[] AllSpendingForMonth() {
        ArrayList<Spending> spending = spendingDao.selectSpending();
        Float[] spendingForMonth = new Float[MESES_ANO];
        Date date = new Date(System.currentTimeMillis());
        String actualYear = new SimpleDateFormat("yyyy").format(date).toString();

        for( int i = 0; i < MESES_ANO; i++ )
            spendingForMonth[i] = 0.0f;

        for ( Spending sp: spending ) {
            int month = Integer.parseInt( sp.getEmissionDate().substring(3, 5) );
            month--;
            String year = sp.getEmissionDate().substring(6, 10);

            if ( year.equals(actualYear) ) {
                spendingForMonth[month] += sp.getAmount();
            }
        }

        return spendingForMonth;
    }
}
