package com.supporthands.supportHands.repository;

public interface BankingDetailsInterface {

    int createBankingDetails(int userId, String bank, String accountType, String accountNumber);
}
