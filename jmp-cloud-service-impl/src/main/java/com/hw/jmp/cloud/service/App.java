package com.hw.jmp.cloud.service;

import com.hw.jmp.bank.impl.BankCardGenerator;
import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.Subscription;
import com.hw.jmp.dto.User;
import com.hw.jmp.cloud.service.exception.SubscriptionNotFoundException;
import com.hw.jmp.cloud.service.impl.ServiceImpl;
import com.hw.jmp.user.impl.Cache;
import com.hw.jmp.user.impl.UserGenerator;

import java.util.Optional;
import java.util.logging.Logger;

public class App {
    private static final Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        var userGenerator = new UserGenerator();
        Cache<Long, User> userCache = userGenerator.generateUserCache();

        var bankCardGenerator = new BankCardGenerator();
        Cache<Long, BankCard> bankCardCache = bankCardGenerator.generateBankCardCache(userCache);

        var service = new ServiceImpl();
        bankCardCache.getAll().forEach((number, card) -> {
            System.out.println(card);
            service.subscribe(card);
            Optional<Subscription> subscriptionOptional = service.getSubscriptionByBankCardNumber(card.getNumber());
            subscriptionOptional.ifPresent(System.out::println);
        });

        try {
            service.getSubscriptionByBankCardNumber("XXX");
        } catch (SubscriptionNotFoundException ex) {
            log.severe(ex.getMessage());
        }

        service.getAllUsers().forEach(System.out::println);
    }
}
