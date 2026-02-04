package com.hw.jmp.bank.impl;

import com.hw.jmp.dto.*;
import com.hw.jmp.api.service.Bank;

import java.util.Random;
import java.util.UUID;

public class BankImpl implements Bank {
    private static final Random RANDOM = new Random();

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        String number = UUID.randomUUID().toString();
        return BankCardType.DEBIT == cardType ?
                new DebitBankCard(number, user, RANDOM.nextDouble(1000)) :
                new CreditBankCard(number, user, RANDOM.nextDouble(10000));
    }
}
