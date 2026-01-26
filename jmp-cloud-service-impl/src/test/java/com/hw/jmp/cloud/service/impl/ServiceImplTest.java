package com.hw.jmp.cloud.service.impl;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.Subscription;
import com.hw.jmp.dto.User;
import com.hw.jmp.cloud.service.exception.SubscriptionNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest {

    private final ServiceImpl service = new ServiceImpl();

    @Test
    void shouldSubscribeUserWithBankCard() {
        // given
        User user = new User("John", "Doe",
                LocalDate.now().minusYears(30));
        BankCard bankCard = new BankCard("1234-5678", user);

        // when
        service.subscribe(bankCard);
        Optional<Subscription> subscription =
                service.getSubscriptionByBankCardNumber(bankCard.getNumber());

        // then
        assertTrue(subscription.isPresent());
        assertEquals(bankCard.getNumber(), subscription.get().getBankcard());
        assertEquals(LocalDate.now(), subscription.get().getStartDate());
    }

    @Test
    void shouldThrowExceptionWhenSubscriptionNotFound() {
        // given
        String cardNumber = "unknown-card";

        // when / then
        SubscriptionNotFoundException exception = assertThrows(
                SubscriptionNotFoundException.class,
                () -> service.getSubscriptionByBankCardNumber(cardNumber)
        );

        assertEquals(
                "Subscription not found for unknown-card",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnAllUniqueUsers() {
        // given
        User user1 = new User("Alice", "Smith",
                LocalDate.now().minusYears(25));
        User user2 = new User("Bob", "Brown",
                LocalDate.now().minusYears(40));

        BankCard card1 = new BankCard("1111", user1);
        BankCard card2 = new BankCard("2222", user2);
        BankCard card3 = new BankCard("3333", user1); // same user, different card

        service.subscribe(card1);
        service.subscribe(card2);
        service.subscribe(card3);

        // when
        List<User> users = service.getAllUsers();

        // then
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSubscribingWithNullCard() {
        // when / then
        assertThrows(
                NullPointerException.class,
                () -> service.subscribe(null)
        );
    }

    @Test
    void shouldThrowNullPointerExceptionWhenGettingSubscriptionWithNullCardNumber() {
        // when / then
        assertThrows(
                NullPointerException.class,
                () -> service.getSubscriptionByBankCardNumber(null)
        );
    }
}

