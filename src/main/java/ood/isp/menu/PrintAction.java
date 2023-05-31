package ood.isp.menu;

public class PrintAction implements UserAction {

    private MenuPrinter printer = new SimpleMenuPrinter();

    @Override
    public String name() {
        return "Print all menu";
    }

    @Override
    public boolean action(Input input, Menu menu) {
        printer.print(menu);
        return true;
    }
}
