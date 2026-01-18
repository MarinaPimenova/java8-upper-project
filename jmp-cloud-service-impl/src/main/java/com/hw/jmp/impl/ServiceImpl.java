package com.hw.jmp.impl;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.Subscription;
import com.hw.jmp.dto.User;
import com.hw.jmp.service.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@SuppressWarnings("unchecked")
public class ServiceImpl implements Service {
    @Override
    public void subscribe(BankCard bankCard) {

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.EMPTY_LIST;
    }
}
