package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
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
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(bankAccount.getBalance()-bankA, bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    void testNegativeWithdraw() {
        fail("To be implemented");
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    void testWithdrawingTooMuch() {
        fail("To be implemented");
    }
}
