package com.hw.jmp;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.User;
import com.hw.jmp.bank.impl.BankCardGenerator;

import com.hw.jmp.user.impl.Cache;
import com.hw.jmp.user.impl.UserGenerator;

import java.util.stream.LongStream;

public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        // Generate users and store them in Cache
        Cache<Long, User> userCache = app.generateUserCache();

        BankCardGenerator bankCardGenerator = new BankCardGenerator();
        Cache<Long, BankCard> bankCardCache = bankCardGenerator.generateBankCardCache(userCache);

        bankCardCache.getAll().forEach((id, card) ->
                System.out.println(id + " -> " + card)
        );
    }

    private Cache<Long, User> generateUserCache()
    {
        Cache<Long, User> userCache = new Cache<>();
        // Generate and cache users using lambda
        LongStream.rangeClosed(1, 10)
                .forEach(i -> userCache.put(i, UserGenerator.randomUserSupplier.get()));
        return userCache;
    }
}
