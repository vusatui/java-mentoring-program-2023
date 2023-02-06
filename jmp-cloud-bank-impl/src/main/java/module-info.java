import com.vusatui.jmt.bank.api.Bank;
import com.vusatyi.jmp.cloud.bank.impl.BankImpl;

module com.vusatui.jmp.cloud.bank.impl {

    requires com.vusatui.jmp.bank.api;
    requires com.vusatui.jmp.dto;

    exports com.vusatyi.jmp.cloud.bank.impl;

    provides Bank with BankImpl;
}