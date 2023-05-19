package ood.lsp.parking;

public class CommonParking implements Valet {
    private final int carPlaces;
    private final int truckPlaces;

    private int countCar;
    private int countTruck;

    public CommonParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public boolean parkTransport(Transport transport) {
        return false;
    }
}
