package net.huray.project.common.data.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.huray.project.common.constant.Constants;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageResponse<T> extends ArrayResponse<T> {

    private long total;

    private int totalPages;

    private int page;

    private int size;

    public static <T> PageResponse<T> of(
            List<T> data,
            int page,
            int size,
            int totalPages,
            long total
    ) {
        return new PageResponseBuilderImpl<T>()
                .data(data)
                .page(page + Constants.DEFAULT_INCREASE_PAGE_NUMBER)
                .size(size)
                .totalPages(totalPages)
                .total(total)
                .build();
    }

    public static <T> PageResponse<T> from(Page<T> result) {
        return PageResponse.of(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalPages(),
                result.getTotalElements()
        );
    }
}
