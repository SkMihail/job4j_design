package ood.lsp.parking;

public class TrailerTruck extends Transport {
    Transport truck;
    public TrailerTruck(Transport truck) {
        this.size = truck.getSize() + 2;
    }
}
