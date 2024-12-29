package com.teste.stoom.ecommerce.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PaginationList<T> {

    public Page<T> pageable(List<T> list, Pageable pageable) {
        int from = Math.max(0, pageable.getPageNumber() * pageable.getPageSize());
        int to = Math.min(list.size(), (pageable.getPageNumber() + 1) * pageable.getPageSize());

        List<T> result = new ArrayList<>();

        if (list.size() > from) {
            result = list.subList(from, to);
        }

        return new PageImpl<>(result, pageable, list.size());
    }

}
