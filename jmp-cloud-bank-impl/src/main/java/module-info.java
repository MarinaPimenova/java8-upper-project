module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    requires jmp.cloud.user.impl;

    exports com.hw.jmp.bank.impl;
}
