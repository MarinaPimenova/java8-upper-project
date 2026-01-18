module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.user.impl;
    requires java.logging;

    exports com.hw.jmp.cloud.service.impl;
}
