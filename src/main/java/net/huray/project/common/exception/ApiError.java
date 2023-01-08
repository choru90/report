package net.huray.project.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.project.common.code.ExceptionCode;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiError {

    private String code;
    private String message;
    private String etc;
    private String reason;
    private String path;

    public static ApiError of(ExceptionCode code, String etc, HttpStatus status, String path) {
        return new ApiError(code.getCode(), code.getMsg(), etc, String.format("%d %s", status.value(), status.getReasonPhrase()), path);
    }

    public static ApiError of(String message, String path) {
        return new ApiError(null, message, null, null, path);
    }
}
