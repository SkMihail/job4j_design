package ood.lsp.controlquality;

import java.util.List;
import java.util.function.Predicate;

public interface Store extends StorageAcceptor {
    void put(Food food);
    List<Food> findBy(Predicate<Food> filter);
    List<Food> findAll();
}
