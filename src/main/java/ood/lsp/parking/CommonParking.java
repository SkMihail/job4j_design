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
        boolean result = false;
        int vehicleSize = transport.getSize();
        if (vehicleSize == 1 && countCar < carPlaces) {
            countCar++;
            result = true;
        } else if (vehicleSize > 1 && countTruck < truckPlaces) {
            countTruck++;
            result = true;
        } else if (vehicleSize > 1 && countTruck == truckPlaces) {
            if (carPlaces - countCar >= vehicleSize) {
                result = true;
                countCar += vehicleSize;
            }
        }
        return result;
    }
}
