package com.gestaoabrigos.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

    public static Pageable createPageable(int page, int size, String sortField, String direction) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(
                Math.max(page - 1, 0),
                Math.min(size, 100),
                Sort.by(sortDirection, sortField)
        );
    }

    public static <T> boolean isLastPage(Page<T> page) {
        return page.getNumber() + 1 >= page.getTotalPages();
    }
}