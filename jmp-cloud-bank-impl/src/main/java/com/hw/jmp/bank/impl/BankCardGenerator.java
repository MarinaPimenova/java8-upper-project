package com.hw.jmp.bank.impl;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.BankCardType;
import com.hw.jmp.dto.User;
import com.hw.jmp.api.service.Bank;
import com.hw.jmp.user.impl.Cache;

import java.util.function.BiFunction;

public class BankCardGenerator {

    private final Bank bank = new BankImpl();

    private final BiFunction<User, BankCardType, BankCard> randomCardFunction = bank::createBankCard;

    public Cache<Long, BankCard> generateBankCardCache(Cache<Long, User> userCache) {
        Cache<Long, BankCard> bankCardCache = new Cache<>();
        userCache.getAll().forEach((key, value) ->
                bankCardCache.put(key, randomCardFunction.apply(value, getBankTypeCard(key))));
        return bankCardCache;
    }

    private BankCardType getBankTypeCard(long i) {
        return i % 2 == 0 ? BankCardType.DEBIT : BankCardType.CREDIT;
    }
}
