package ood.lsp.controlquality;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class WarehouseTest {

    @Test
    public void testCheckFilter() {
        Warehouse warehouse = new Warehouse();
        Food apple = new Food(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(4), 100);
        Food cheese = new Food(
                "Голландский", LocalDateTime.now().minusDays(5), LocalDateTime.now(), 20);
        assertThat(warehouse.check(apple)).isTrue();
        assertThat(warehouse.check(cheese)).isFalse();
    }
    @Test
    public void testPut() {
        Warehouse warehouse = new Warehouse();
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 100);
        warehouse.put(apple);
        assertThat(warehouse.foodList).contains(apple);
    }

    @Test
    public void testFindBy() {
        Warehouse warehouse = new Warehouse();
        Food carrot = new Vegetables(
                "carrot",  LocalDateTime.now().minusDays(5), LocalDateTime.now(), 90);
        warehouse.put(carrot);
        assertThat(warehouse.findBy(a -> a.getCreateDate().isBefore(LocalDateTime.now()))).contains(carrot);
    }
    @Test
    public void testFindAll() {
        Warehouse warehouse = new Warehouse();
        Food banana = new Fruit(
                "banana", LocalDateTime.now().minusDays(3), LocalDateTime.now(), 80);
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 100);
        warehouse.put(banana);
        warehouse.put(apple);
        assertThat(warehouse.findAll()).containsExactly(banana, apple);
    }
}