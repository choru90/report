package net.huray.project.common.exception;

import lombok.Getter;
import net.huray.project.common.code.ExceptionCode;

@Getter
public class StatusException extends RuntimeException {

    private ExceptionCode code;
    private String etc;

    public StatusException(ExceptionCode code) {
        this.code = code;
    }

    public StatusException(ExceptionCode code, String etc) {
        this.code = code;
        this.etc = etc;
    }
}
