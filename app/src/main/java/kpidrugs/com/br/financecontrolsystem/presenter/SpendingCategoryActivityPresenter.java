package kpidrugs.com.br.financecontrolsystem.presenter;

import android.content.Context;

import java.util.ArrayList;

import kpidrugs.com.br.financecontrolsystem.dao.SpendingDao;
import kpidrugs.com.br.financecontrolsystem.model.Spending;

public class SpendingCategoryActivityPresenter {
    SpendingCategoryInterface mView;
    Context c;
    SpendingDao spendingDao;

    public SpendingCategoryActivityPresenter(SpendingCategoryInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public Float[] spendingForCategory(String monthYear) {
        ArrayList<String> category = mView.getCategory();
        ArrayList<Spending> spending = spendingDao.selectSpendingMonthYear(monthYear);
        Float[] spendingForCategory = new Float[ category.size() ];

        for( int i = 0; i < spendingForCategory.length; i++ )
            spendingForCategory[i] = 0.0f;

        for ( Spending sp: spending ) {
            for ( int i = 0; i < category.size(); i++ ) {
                if ( sp.getCategory().equalsIgnoreCase( category.get(i) ) ) {
                    spendingForCategory[i] += sp.getAmount();
                }
            }
        }

        return spendingForCategory;
    }
}
