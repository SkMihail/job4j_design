package ood.isp.menu;

public class AddRootAction implements UserAction {

    @Override
    public String name() {
        return "Add ROOT task";
    }

    @Override
    public boolean action(Input input, Menu menu) {
        String newTask = input.askStr("Enter root task");
        if (menu.add(Menu.ROOT, newTask, DEFAULT_ACTION)) {
            System.out.println("Root task added successfully");
        }
        return true;
    }
}
