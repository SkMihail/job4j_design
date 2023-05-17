package ood.lsp.controlquality;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    public void testCheckFilter() {
        Trash trash = new Trash();
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(4), 100);
        Food cheese = new Food(
                "Голландский", LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(1), 20);
        assertThat(trash.check(apple)).isFalse();
        assertThat(trash.check(cheese)).isTrue();
    }
    @Test
    public void testPut() {
        Trash trash = new Trash();
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 100);
        trash.put(apple);
        assertThat(trash.foodList).contains(apple);
    }

    @Test
    public void testFindBy() {
        Trash trash = new Trash();
        Food carrot = new Vegetables(
                "carrot",  LocalDateTime.now().minusDays(5), LocalDateTime.now(), 90);
        trash.put(carrot);
        assertThat(trash.findBy(a -> a.getCreateDate().isBefore(LocalDateTime.now()))).contains(carrot);
    }
    @Test
    public void testFindAll() {
        Trash trash = new Trash();
        Food banana = new Fruit(
                "banana", LocalDateTime.now().minusDays(3), LocalDateTime.now(), 80);
        Food apple = new Fruit(
                "apple",  LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 100);
        trash.put(banana);
        trash.put(apple);
        assertThat(trash.findAll()).containsExactly(banana, apple);
    }

}