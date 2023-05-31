package ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            int length = i.getNumber().length();
            System.out.println(
                    "-".repeat(length > 2 ? length - 2 : 0) + i.getNumber() + i.getName());
        });
    }
}
