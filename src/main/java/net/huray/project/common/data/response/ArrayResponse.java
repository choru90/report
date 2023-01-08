package net.huray.project.common.data.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArrayResponse<T> {

    private List<T> data;

    public static <T> ArrayResponse<T> from(List<T> data) {

        return new ArrayResponseBuilderImpl<T>()
                .data(data)
                .build();
    }
}
