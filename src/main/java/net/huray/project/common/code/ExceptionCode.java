package net.huray.project.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
public enum ExceptionCode {
    ERR001("ERR001", NOT_FOUND, "리소스를 찾을 수 없습니다.");

    @Getter
    private String code;

    @Getter
    private HttpStatus status;

    @Getter
    private String msg;

}
