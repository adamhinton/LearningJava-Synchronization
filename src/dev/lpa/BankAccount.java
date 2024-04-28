package dev.lpa;

public class BankAccount {

    private double balance;
    private String name;

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Don't want wo threads to set name at a time
    public synchronized void setName(String name) {
        this.name = name;
        System.out.println("Updated name = " + name);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit (double amount){

        try{
            System.out.println("Deposit - talking to teller");
            Thread.sleep(7000);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        synchronized (this){
            double origBalance = balance;
            balance += amount;
            System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" + " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
        }

    }


    public synchronized void withdraw (double amount){
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        double origBalance = balance;

        if(amount <= balance){
            balance -= amount;
            System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f)" + " : NEW BALANCE = %.0f%n", origBalance, amount, balance);
        }
        else{
            System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.Of)" + ": INSUFFICIENT FUNDS!", origBalance, amount);
        }

    }

}
