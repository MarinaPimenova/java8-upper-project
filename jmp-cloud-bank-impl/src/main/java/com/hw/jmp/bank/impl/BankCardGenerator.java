package com.hw.jmp.bank.impl;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.BankCardType;
import com.hw.jmp.dto.User;
import com.hw.jmp.api.service.Bank;
import com.hw.jmp.user.impl.Cache;

import java.util.function.BiFunction;

import java.util.stream.LongStream;

public class BankCardGenerator {

    private final Bank bank = new BankImpl();

    public Cache<Long, BankCard> generateBankCardCache(Cache<Long, User> userCache) {
        Cache<Long, BankCard> bankCardCache = new Cache<>();
        LongStream.rangeClosed(0, userCache.getAll().size() - 1)
                .forEach(i ->
                        bankCardCache.put(i, randomCardFunction.apply(userCache.get(i),
                                getBankTypeCard(i))));
        return bankCardCache;
    }

    private BankCardType getBankTypeCard(long i) {
        return i % 2 == 0 ? BankCardType.DEBIT : BankCardType.CREDIT;
    }

    private final BiFunction<User, BankCardType, BankCard> randomCardFunction = (user, cardType) ->
            bank.createBankCard(user, cardType);

}
