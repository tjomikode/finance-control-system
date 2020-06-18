package kpidrugs.com.br.financecontrolsystem.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import kpidrugs.com.br.financecontrolsystem.R;
import kpidrugs.com.br.financecontrolsystem.presenter.BalanceActivityPresenter;
import kpidrugs.com.br.financecontrolsystem.presenter.BalanceInterface;

public class BalanceActivity extends AppCompatActivity implements BalanceInterface {
    private Spinner spnAccount, spnCard;
    private EditText edtBalance, edtCredit;
    private BalanceActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        spnAccount = (Spinner) findViewById(R.id.spnAccount);
        spnCard = (Spinner) findViewById(R.id.spnCard);
        edtBalance = (EditText) findViewById(R.id.edtBalance);
        edtBalance.setEnabled(false);
        edtCredit = (EditText) findViewById(R.id.edtCredit);
        edtCredit.setEnabled(false);
        mPresenter = new BalanceActivityPresenter(this, this.getApplicationContext());
        addAccountSpinner();
        addCardSpinner();

        spnAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                edtBalance.setText( mPresenter.getBalanceAccount( spnAccount.getSelectedItem().toString() ) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                return;
            }
        });

        spnCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtCredit.setText( mPresenter.getCredit( spnCard.getSelectedItem().toString() ) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }


    public void addAccountSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        ArrayList<String> arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCard.setAdapter(adapter);
    }
}
