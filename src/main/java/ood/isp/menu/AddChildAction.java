package ood.isp.menu;

public class AddChildAction implements UserAction {
    @Override
    public String name() {
        return "Add child task";
    }

    @Override
    public boolean action(Input input, Menu menu) {
        String parentTask = input.askStr("Enter parent task name");
        String newTask = input.askStr("Enter subtask");
        if (!menu.add(parentTask, newTask, DEFAULT_ACTION)) {
            System.out.println("Sorry, here is no such parent task, choose another action");
        } else {
            System.out.println("task added successfully");
        }
        return true;
    }
}
