package ood.lsp.controlquality;

import java.time.LocalDateTime;

public class Fruit extends Food {
    public Fruit(String name, LocalDateTime createDate, LocalDateTime expiryDate, float price) {
        super(name, createDate, expiryDate, price);
    }
}
