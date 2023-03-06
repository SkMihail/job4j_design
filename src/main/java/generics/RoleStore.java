package generics;

public class RoleStore implements Store<Role> {

    private final Store<Role> roleStorage = new MemStore<>();

    @Override
    public void add(Role model) {
        roleStorage.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return roleStorage.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return roleStorage.delete(id);
    }

    @Override
    public Role findById(String id) {
        return roleStorage.findById(id);
    }
}
