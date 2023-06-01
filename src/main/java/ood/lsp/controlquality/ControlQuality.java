package ood.lsp.controlquality;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public void sort(Food food) {
        for (Store store : storages) {
            if (store.check(food)) {
                store.put(food);
                break;
            }
        }
    }

    public void reSort() {
        List<Food> temp = new ArrayList<>();
        for (Store store : storages) {
            temp.addAll(store.findAll());
            store.findAll().clear();
        }
        temp.forEach(this::sort);
    }

    List<Store> getStorages() {
        return storages;
    }
}

