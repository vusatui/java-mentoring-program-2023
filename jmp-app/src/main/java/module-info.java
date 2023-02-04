module com.vusatui.jmp.app {
    requires com.vusatui.jmp.bank.api;
    requires com.vusatui.jmp.service.api;
    requires com.vusatui.jmp.cloud.bank.impl;
    requires com.vusatui.jmp.cloud.service.impl;
    requires com.vusatui.jmp.dto;

    uses com.vusatui.jmt.bank.api.Bank;
    uses com.vusatui.jmp.service.api.Service;
}