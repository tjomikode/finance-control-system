package kpidrugs.com.br.financecontrolsystem.model;

public class Account {
    private String bankName;
    private double balance;
    private int codUser;

    public Account(String bankName, double balance, int codUser) {
        this.bankName = bankName;
        this.balance = balance;
        this.codUser = codUser;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCodUser() {
        return codUser;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
