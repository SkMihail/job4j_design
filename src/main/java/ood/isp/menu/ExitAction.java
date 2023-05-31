package ood.isp.menu;

public class ExitAction implements UserAction {

    @Override
    public String name() {
        return "Exit from ToDoApp";
    }

    @Override
    public boolean action(Input input, Menu menu) {
        System.out.println("Exit");
        return false;
    }
}
