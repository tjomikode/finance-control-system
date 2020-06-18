package kpidrugs.com.br.financecontrolsystem.presenter;

public interface AccountInterface {
    String getBankName();
    String getReceiptDate();
    String getBalance();
    void registrationError();
    void successfullyInserted();
    void databaseInsertError();
}
