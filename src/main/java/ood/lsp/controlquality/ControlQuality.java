package ood.lsp.controlquality;

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

    List<Store> getStorages() {
        return storages;
    }
}

