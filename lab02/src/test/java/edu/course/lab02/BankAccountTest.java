package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    void constructorAcceptsNonNegativeBalance() {
        BankAccount account = new BankAccount(250);
        assertEquals(250, account.getBalance());
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-50));
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount(250);
        account.deposit(75);
        assertEquals(325, account.getBalance());
    }

    @Test
    void depositRejectsZeroAndNegative() {
        BankAccount account = new BankAccount(250);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-20));
        // Проверяем, что баланс не изменился после ошибок
        assertEquals(250, account.getBalance());
    }

    @Test
    void withdrawDecreasesBalance() {
        BankAccount account = new BankAccount(250);
        account.withdraw(90);
        assertEquals(160, account.getBalance());
    }

    @Test
    void withdrawRejectsZeroAndNegative() {
        BankAccount account = new BankAccount(250);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-15));
        assertEquals(250, account.getBalance());
    }

    @Test
    void withdrawRejectsAmountGreaterThanBalance() {
        BankAccount account = new BankAccount(250);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(300));
        assertEquals(250, account.getBalance());
    }
}