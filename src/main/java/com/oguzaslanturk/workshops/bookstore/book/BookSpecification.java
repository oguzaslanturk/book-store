package com.oguzaslanturk.workshops.bookstore.book;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> isNameContains(String name) {
        return (root, query, builder) -> {
            if (StringUtils.isNotBlank(name)) {
                return builder.like(root.get(Book_.name), "%" + name + "%");
            }
            return builder.isTrue(builder.literal(true));
        };
    }

    public static Specification<Book> hasIsbn(String isbn) {
        return (root, query, builder) -> {
            if (StringUtils.isNotBlank(isbn)) {
                return builder.equal(root.get(Book_.isbn), isbn);
            }
            return builder.isTrue(builder.literal(true));
        };
    }
}