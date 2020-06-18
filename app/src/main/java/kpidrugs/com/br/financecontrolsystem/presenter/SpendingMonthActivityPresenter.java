package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.util.ArrayList;

import kpidrugs.com.br.financecontrolsystem.dao.SpendingDao;
import kpidrugs.com.br.financecontrolsystem.model.Spending;

public class SpendingMonthActivityPresenter {
    private SpendingMonthInterface mView;
    private Context c;
    SpendingDao spendingDao;

    public SpendingMonthActivityPresenter(SpendingMonthInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public ArrayList<Spending> getSpendingForMonthYear() {
        String monthYear = mView.getMonthYear();

        return spendingDao.selectSpendingMonthYear(monthYear);
    }
}
