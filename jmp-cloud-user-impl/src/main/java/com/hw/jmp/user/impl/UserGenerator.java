package com.hw.jmp.user.impl;

import com.hw.jmp.dto.User;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;

public class UserGenerator {

    private static final Random RANDOM = new Random();

    public static Supplier<User> randomUserSupplier = () -> {
        String name = "Name" + RANDOM.nextInt(1000);
        String surname = "Surname" + RANDOM.nextInt(1000);

        LocalDate birthday = LocalDate.now()
                .minusYears(18 + RANDOM.nextInt(40)) // age 18–57
                .minusDays(RANDOM.nextInt(365));

        return new User(name, surname, birthday);
    };
}

