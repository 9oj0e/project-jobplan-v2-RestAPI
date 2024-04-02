package shop.mtcoding.projectjobplan._core.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PagingUtil {

    public static <T> Page<T> pageConverter(Pageable pageable, List<T> content) {
        // Page = 0 페이지 부터 시작.
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), content.size());

        List<T> pageData = new ArrayList<>();
        if (start <= end) {
            pageData = content.subList(start, end);
        }
        return new PageImpl<>(pageData, pageable, content.size());
    }

    public static List<Integer> getPageList(Page<?> page) {
        List<Integer> pageNumbers = new ArrayList<>();

        for (int i = 0; i < page.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }
}
