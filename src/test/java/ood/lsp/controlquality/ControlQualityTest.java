package ood.lsp.controlquality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    ControlQuality controller;
    Store warehouse = new Warehouse();
    Store shop = new Shop();
    Store trash = new Trash();

    @BeforeEach
    public void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        controller = new ControlQuality(List.of(warehouse, shop, trash));
    }

    @Test
    public void testSort() {
        Food cheese = new Food(
                "Голландский", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(5), 20);
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(4), 100);
        Food cheese2 = new Food(
                "Голландский", LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(1), 5);
        controller.sort(cheese2);
        controller.sort(cheese);
        controller.sort(apple);
        assertThat(warehouse.findAll()).contains(apple);
        assertThat(shop.findAll()).contains(cheese);
        assertThat(trash.findAll()).contains(cheese2);
    }
    @Test
    public void testReSort() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Food(
                "Голландский", time.minusDays(5), time.plusDays(5), 20);
        Food apple = new Fruit(
                "apple",  time.minusDays(1), time.plusDays(4), 100);
        Food cheese2 = new Food(
                "Голландский", time.minusDays(5), time.minusDays(1), 5);
        controller.sort(cheese2);
        controller.sort(cheese);
        controller.sort(apple);
        assertThat(warehouse.findAll()).contains(apple);
        assertThat(shop.findAll()).contains(cheese);
        assertThat(trash.findAll()).contains(cheese2);
        warehouse.findAll().get(0).setCreateDate(time.plusDays(10));
        shop.findAll().get(0).setCreateDate(time.plusDays(10));
        controller.reSort();
        assertThat(trash.findAll()).contains(cheese2, cheese, apple);
    }
    @Test
    public void testGetStorages() {
        assertThat(controller.getStorages()).contains(warehouse, shop, trash);
    }

    @Test
    public void testSetDiscountForCheeseWith23PercentOfFreshness() {
        Food cheese = new Food(
                "Голландский", LocalDateTime.now().minusDays(10), LocalDateTime.now().plusDays(3), 20);
        controller.sort(cheese);
        assertThat(shop.findBy(a -> a.getDiscount() == 50)).contains(cheese);
        assertThat(shop.findBy(a -> a.getDiscount() == 50).get(0).getPrice()).isEqualTo(10);
    }
}