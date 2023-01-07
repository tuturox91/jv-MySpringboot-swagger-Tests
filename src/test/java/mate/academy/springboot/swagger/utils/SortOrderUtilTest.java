package mate.academy.springboot.swagger.utils;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.*;

class SortOrderUtilTest {

    @Test
    public void OneParameterWithDirection_ok() {
        String sortBy = "price:DESC";
        List<Sort.Order> orderList = SortOrderUtil.parseSort(sortBy);
        assertEquals(1, orderList.size());
        assertEquals(Sort.Direction.DESC, orderList.get(0).getDirection());
        assertEquals("price", orderList.get(0).getProperty());
    }

    @Test
    public void TwoParametersWithDirection_ok() {
        String sortBy = "price:DESC;title:ASC";
        List<Sort.Order> orderList = SortOrderUtil.parseSort(sortBy);
        assertEquals(2, orderList.size());
        assertEquals(Sort.Direction.DESC, orderList.get(0).getDirection());
        assertEquals("price", orderList.get(0).getProperty());
        assertEquals(Sort.Direction.ASC, orderList.get(1).getDirection());
        assertEquals("title", orderList.get(1).getProperty());
    }

    @Test
    public void OneParameterWithDirectionAndSemicolon_ok() {
        String sortBy = "price:DESC;";
        List<Sort.Order> orderList = SortOrderUtil.parseSort(sortBy);
        assertEquals(1, orderList.size());
        assertEquals(Sort.Direction.DESC, orderList.get(0).getDirection());
        assertEquals("price", orderList.get(0).getProperty());
    }

    @Test
    public void OneParameterWithoutDirectionDefaultDesc_ok() {
        String sortBy = "price";
        List<Sort.Order> orderList = SortOrderUtil.parseSort(sortBy);
        assertEquals(1, orderList.size());
        assertEquals(Sort.Direction.DESC, orderList.get(0).getDirection());
        assertEquals("price", orderList.get(0).getProperty());
    }


    @Test
    public void TwoParametersWithoutDirectionDefaultDESC_ok() {
        String sortBy = "price;title";
        List<Sort.Order> orderList = SortOrderUtil.parseSort(sortBy);
        assertEquals(2, orderList.size());
        assertEquals(Sort.Direction.DESC, orderList.get(0).getDirection());
        assertEquals("price", orderList.get(0).getProperty());
        assertEquals(Sort.Direction.DESC, orderList.get(1).getDirection());
        assertEquals("title", orderList.get(1).getProperty());
    }
}