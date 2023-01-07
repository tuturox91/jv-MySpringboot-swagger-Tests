package mate.academy.springboot.swagger.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;

public class SortOrderUtil {
    public static List<Sort.Order> parseSort(String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(":")) {
            String[] sortingFields = sortBy.split(";");
            for (String field : sortingFields) {
                Sort.Order order;
                if (field.contains(":")) {
                    String[] fieldsWithDirections = field.split(":");
                    order = new Sort.Order(Sort.Direction.valueOf(fieldsWithDirections[1]),
                            fieldsWithDirections[0]);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, field);
                }
                orders.add(order);
            }
        } else {
            if (sortBy.contains(";")) {
                String[] sortingFields = sortBy.split(";");
                for (String str : sortingFields) {
                    Sort.Order order;
                    order = new Sort.Order(Sort.Direction.DESC, str);
                    orders.add(order);
                }
            } else {
                Sort.Order order = new Sort.Order(Sort.Direction.DESC, sortBy);
                orders.add(order);
            }
        }
        return orders;
    }
}
