import com.vusatui.jmt.bank.api.Bank;
import com.vusatyi.jmp.cloud.bank.impl.BankImpl;

module com.vusatui.jmp.cloud.bank.impl {

    requires com.vusatui.jmp.bank.api;
    requires com.vusatui.jmp.dto;

    exports com.vusatyi.jmp.cloud.bank.impl;
    exports com.vusatyi.jmp.cloud.bank.exception;

    provides Bank with BankImpl;
}