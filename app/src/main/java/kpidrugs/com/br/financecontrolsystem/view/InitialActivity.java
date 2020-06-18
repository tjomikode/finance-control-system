package kpidrugs.com.br.financecontrolsystem.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kpidrugs.com.br.financecontrolsystem.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kpidrugs.com.br.financecontrolsystem.presenter.InitialActivityPresenter;
import kpidrugs.com.br.financecontrolsystem.presenter.InitialInterface;

public class InitialActivity extends AppCompatActivity implements InitialInterface {
    private EditText name, email;
    private Button btClean, btSubmit;
    private InitialActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        name = (EditText) findViewById(R.id.edtName);
        email = (EditText) findViewById(R.id.edtEmail);
        btSubmit = (Button) findViewById(R.id.btnSubmit);
        btClean = (Button) findViewById(R.id.btnClean);
        mPresenter = new InitialActivityPresenter(this, this.getApplicationContext());

        btClean.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanEditText();
            }
        });

        btSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mPresenter.initialRegistration() ) {
                    finish();
                }
            }
        });
    }

    // Clean EditText
    public void cleanEditText() {
        name.requestFocus();
        name.setText(null);
        email.setText(null);
    }

    public String getName() {
        return name.getText().toString();
    }

    public String getEmail() {
        return email.getText().toString();
    }

    public void registrationError() {
        Toast.makeText(InitialActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(InitialActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void DatabaseInsertError() {
        Toast.makeText(InitialActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }
}