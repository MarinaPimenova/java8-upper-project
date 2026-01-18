package com.hw.jmp;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.User;
import com.hw.jmp.bank.impl.BankCardGenerator;

import com.hw.jmp.user.impl.Cache;
import com.hw.jmp.user.impl.UserGenerator;

public class App 
{
    public static void main( String[] args )
    {

        // Generate users and store them in Cache
        UserGenerator userGenerator = new UserGenerator();
        Cache<Long, User> userCache = userGenerator.generateUserCache();

        BankCardGenerator bankCardGenerator = new BankCardGenerator();
        Cache<Long, BankCard> bankCardCache = bankCardGenerator.generateBankCardCache(userCache);

        bankCardCache.getAll().forEach((id, card) ->
                System.out.println(id + " -> " + card)
        );
    }

}
