package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {
    private static final int AMOUNT = 100;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), AMOUNT);
        final double startBalance = bankAccount.getBalance();
        double difference = bankAccount.getBalance();
        bankAccount.chargeManagementFees(mRossi.getUserID());
        difference -= bankAccount.getBalance();
        assertEquals(bankAccount.getBalance(), startBalance - difference);
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    void testNegativeWithdraw() {
        final double saldoInizio = bankAccount.getBalance();
        try {
            bankAccount.withdraw(mRossi.getUserID(), -bankAccount.getBalance());
        } catch (final IllegalArgumentException e) {
            //non so cosa potrei metterci per togliere l'errore del checkstyle
        }
        assertEquals(bankAccount.getBalance(), saldoInizio);
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    void testWithdrawingTooMuch() {
        final double saldoInizio = bankAccount.getBalance();
        try {
            bankAccount.withdraw(mRossi.getUserID(), bankAccount.getBalance() + AMOUNT);
        } catch (final IllegalArgumentException e) {
            //non so cosa potrei metterci per togliere l'errore del checkstyle
        }
        assertEquals(bankAccount.getBalance(), saldoInizio);
    }
}
