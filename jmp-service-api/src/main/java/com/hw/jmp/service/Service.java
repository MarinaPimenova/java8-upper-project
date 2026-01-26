package com.hw.jmp.service;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.Subscription;
import com.hw.jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    default double getAverageUsersAge() {
        LocalDate today = LocalDate.now();
        return getAllUsers().stream()
                .mapToDouble(user ->
                        ChronoUnit.YEARS.between(user.getBirthday(), today)
                )
                .average()
                .orElse(0.0);
    }

    // returns true, if the user is over 18 years old
    static boolean isPayableUser(User user) {
        if (user == null || user.getBirthday() == null) {
            return false;
        }

        var age = ChronoUnit.YEARS.between(
                user.getBirthday(),
                LocalDate.now()
        );

        return age >= 18;
    }
}
