package edu.course.lab02;

public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance){
        if (initialBalance < 0) {
             throw new IllegalArgumentException(
                "Начальный баланс отрицательный: " + initialBalance
            );
        }
        this.balance = initialBalance;
    }
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма внесения должна быть положительной и не равна нулю" + amount
            );
        }
        balance += amount;
    }


    
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма снятия должна быть положительной и не равна нулю" + amount
            );
        }
        if (amount > balance) {
            throw new IllegalArgumentException(
                "Сумма снятия должна быть меньше суммы на балансе"
            );
        }
        balance -= amount;
    }


    
    public int getBalance() {
        return balance;
    }

}

