package dev.lpa;

public class Main {
    public static void main(String[] args) {

        BankAccount companyAccount = new BankAccount(10_000);

        Thread thread1 = new Thread(() -> companyAccount.withdraw(2500));
        Thread thread2  = new Thread(() -> companyAccount.deposit(5000));
        Thread thread3  = new Thread(() -> companyAccount.withdraw(2500));
        Thread thread4  = new Thread(() -> companyAccount.withdraw(5000));

        // No actual guarantee which will start first
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Final balance: " + companyAccount.getBalance());

    }
}