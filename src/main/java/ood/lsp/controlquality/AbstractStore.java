package ood.lsp.controlquality;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store {
    protected List<Food> foodList;
    protected StorageAcceptor acceptor;

    public AbstractStore() {
        this.foodList = new ArrayList<>();
    }

    @Override
    public void put(Food food) {
        foodList.add(food);
    }

    @Override
    public boolean check(Food food) {
        return acceptor.check(food);
    }

    @Override
    public List<Food> findAll() {
        return foodList;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foodList.stream().filter(filter).collect(Collectors.toList());
    }
}
