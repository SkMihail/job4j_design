package ood.lsp.controlquality;

import java.time.LocalDateTime;

public class Vegetables extends Food {
    public Vegetables(String name, LocalDateTime createDate, LocalDateTime expiryDate, float price) {
        super(name, createDate, expiryDate, price);
    }
}
