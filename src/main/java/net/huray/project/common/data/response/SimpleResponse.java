package net.huray.project.common.data.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleResponse<T> {

    private T data;

    public static <T> SimpleResponse<T> from(T data) {
        SimpleResponseBuilder<T> builder = SimpleResponse.builder();

        return builder
                .data(data)
                .build();
    }
}
