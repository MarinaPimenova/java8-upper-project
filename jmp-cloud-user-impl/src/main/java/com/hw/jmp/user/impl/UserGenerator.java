package com.hw.jmp.user.impl;

import com.hw.jmp.dto.User;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class UserGenerator {

    private static final Random RANDOM = new Random();

    public Cache<Long, User> generateUserCache()
    {
        Cache<Long, User> userCache = new Cache<>();
        // Generate and cache users using lambda
        LongStream.rangeClosed(1, 10)
                .forEach(i -> userCache.put(i, randomUserSupplier.get()));
        return userCache;
    }

    Supplier<User> randomUserSupplier = () -> {
        String name = "Name" + RANDOM.nextInt(1000);
        String surname = "Surname" + RANDOM.nextInt(1000);

        LocalDate birthday = LocalDate.now()
                .minusYears(18 + RANDOM.nextInt(40)) // age 18–57
                .minusDays(RANDOM.nextInt(365));

        return new User(name, surname, birthday);
    };
}

