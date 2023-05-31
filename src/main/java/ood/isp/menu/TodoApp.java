package ood.isp.menu;

import java.util.List;

public class TodoApp {
    public void init(Input input, Menu menu, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                System.out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.action(input, menu);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Input console = new ConsoleInput();
        List<UserAction> actions = List.of(
                new AddRootAction(), new AddChildAction(), new PrintAction(), new DoAction(), new ExitAction());
        Menu menu = new SimpleMenu();
        TodoApp app = new TodoApp();
        app.init(console, menu, actions);
    }
}
