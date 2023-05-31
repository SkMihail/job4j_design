package ood.isp.menu;

import java.util.Optional;

public class DoAction implements UserAction {
    @Override
    public String name() {
        return "Do task action";
    }

    @Override
    public boolean action(Input input, Menu menu) {
        String taskName = input.askStr("Enter task name");
        Optional<Menu.MenuItemInfo> task = menu.select(taskName);
        if (task.isPresent()) {
            task.get().getActionDelegate().delegate();
        } else {
            System.out.println("Sorry, here is no such task, choose another action");
        }
        return true;
    }
}
