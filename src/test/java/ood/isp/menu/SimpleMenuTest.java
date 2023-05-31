package ood.isp.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    Menu menu = new SimpleMenu();

    @BeforeEach
    public void setUp() {
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
    }

    @Test
    public void whenAddThenReturnSame() {
        assertThat(menu.select("Сходить в магазин").get())
                .isEqualTo(new Menu.MenuItemInfo("Сходить в магазин",
                        List.of("Купить продукты"), STUB_ACTION, "1."));
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectUnExistThenReturnEmpty() {
        assertThat(menu.select("Сходить")).isEqualTo(Optional.empty());
    }

    @Test
    public void whenPrintTest() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        MenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);
        String ln = System.lineSeparator();
        String expected = "1.Сходить в магазин" + ln
                + "--1.1.Купить продукты" + ln
                + "----1.1.1.Купить хлеб" + ln
                + "----1.1.2.Купить молоко" + ln
                + "2.Покормить собаку" + ln;
        assertThat(outputStreamCaptor.toString()).isEqualTo(expected);
    }
}