package ood.lsp.controlquality;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void testCheckFilter() {
        Shop shop = new Shop();
        Food apple = new Food(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(4), 100);
        Food cheese = new Food(
                "Голландский", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(5), 20);
        assertThat(shop.check(apple)).isFalse();
        assertThat(shop.check(cheese)).isTrue();
    }
    @Test
    public void testPut() {
        Shop shop = new Shop();
        Food apple = new Food(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 100);
        shop.put(apple);
        assertThat(shop.foodList).contains(apple);
    }

    @Test
    public void testFindBy() {
        Shop shop = new Shop();
        Food carrot = new Vegetables(
                "carrot",  LocalDateTime.now().minusDays(5), LocalDateTime.now(), 90);
        shop.put(carrot);
        assertThat(shop.findBy(a -> a.getCreateDate().isBefore(LocalDateTime.now()))).contains(carrot);
    }
    @Test
    public void testFindAll() {
        Shop shop = new Shop();
        Food banana = new Fruit(
                "banana", LocalDateTime.now().minusDays(3), LocalDateTime.now(), 80);
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 100);
        shop.put(banana);
        shop.put(apple);
        assertThat(shop.findAll()).containsExactly(banana, apple);
    }
}