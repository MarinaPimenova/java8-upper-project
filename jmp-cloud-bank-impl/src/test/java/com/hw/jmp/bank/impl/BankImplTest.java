package com.hw.jmp.bank.impl;

import com.hw.jmp.dto.BankCard;
import com.hw.jmp.dto.BankCardType;
import com.hw.jmp.dto.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BankImplTest {
    private final BankImpl service = new BankImpl();

    @Test
    void shouldCreateBankCard() {
        // given
        String name = "Name#1";
        String surname = "Surname#1";

        LocalDate birthday = LocalDate.now()
                .minusYears(18 + 10) // age 18–57
                .minusDays(20);
        User user = new User(name, surname, birthday);

        // when
        BankCard result = service.createBankCard(user, BankCardType.DEBIT);

        // then
        assertion(result, name, surname, birthday);

        // given
        name = "Name#2";
        surname = "Surname#2";
        birthday = LocalDate.now()
                .minusYears(18 + 11) // age 18–57
                .minusDays(21);
        user = new User(name, surname, birthday);

        // when
        result = service.createBankCard(user, BankCardType.CREDIT);

        // then
        assertion(result, name, surname, birthday);
    }

    private void assertion(BankCard result, String name, String surname, LocalDate birthday) {
        assertNotNull(result);
        assertNotNull(result.getNumber());
        assertNotNull(result.getUser());
        assertEquals(result.getUser().getName(), name);
        assertEquals(result.getUser().getSurname(), surname);
        assertEquals(result.getUser().getBirthday(), birthday);
    }
}
