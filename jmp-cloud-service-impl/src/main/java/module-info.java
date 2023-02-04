import com.vusatui.jmp.cloud.service.impl.ServiceImpl;
import com.vusatui.jmp.service.api.Service;

module com.vusatui.jmp.cloud.service.impl {

    requires com.vusatui.jmp.service.api;
    requires com.vusatui.jmp.dto;
    requires com.vusatui.jmp.cloud.bank.impl;

    exports com.vusatui.jmp.cloud.service.impl;
    exports com.vusatui.jmp.cloud.service.impl.exception;

    provides Service with ServiceImpl;
}