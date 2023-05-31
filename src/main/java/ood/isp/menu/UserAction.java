package ood.isp.menu;

public interface UserAction {
    ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    String name();
    boolean action(Input input, Menu menu);
}
