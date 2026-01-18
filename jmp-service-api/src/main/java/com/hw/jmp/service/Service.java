package com.hw.jmp.service;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.Subscription;
import com.hw.jmp.dto.User;

import java.util.List;
import java.util.Optional;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();
}
