package com.hw.jmp.cloud.service.impl;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.Subscription;
import com.hw.jmp.dto.User;
import com.hw.jmp.cloud.service.exception.SubscriptionNotFoundException;
import com.hw.jmp.service.Service;
import com.hw.jmp.user.impl.Cache;

import java.time.LocalDate;
import java.util.*;

import static java.lang.String.format;

public class ServiceImpl implements Service {

    private final Cache<String, User> userCache = new Cache<>();

    private final Cache<String, Subscription> subscriptionCache = new Cache<>();

    @Override
    public void subscribe(BankCard bankCard) {
        Objects.requireNonNull(bankCard, "BankCard can't be null");
        subscriptionCache.put(bankCard.getNumber(), new Subscription(bankCard.getNumber(), LocalDate.now()));
        userCache.put(bankCard.getNumber(), bankCard.getUser());
    }

    //  handle scenarios where a subscription is not found,
    //  specifically for use with the orElseThrow method on Optional
    //  within the getSubscriptionByBankCardNumber
    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        Objects.requireNonNull(cardNumber, "cardNumber cannot be null");
        return Optional.ofNullable(subscriptionCache.getAll().values()
                .stream().filter(item -> cardNumber.equals(item.getBankcard()))
                .findFirst()
                .orElseThrow(() ->
                        new SubscriptionNotFoundException(format("Subscription not found for %s", cardNumber))));

    }

    @Override
    public List<User> getAllUsers() {
        var users = new HashSet<>(userCache.getAll().values());
        return new ArrayList<>(users);
    }
}
