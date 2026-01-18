package com.hw.jmp.service;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.BankCardType;
import com.hw.jmp.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType cardType);
}
