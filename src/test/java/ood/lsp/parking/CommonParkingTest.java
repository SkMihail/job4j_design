package ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CommonParkingTest {

    @Test
    public void whenParkingIsFreeAndEnoughThenAllTrue() {
        CommonParking parking = new CommonParking(1, 2);
        Transport car = new Car();
        Transport truck = new Truck();
        Transport trailerTruck = new TrailerTruck(truck);
        assertThat(parking.parkTransport(car)).isTrue();
        assertThat(parking.parkTransport(truck)).isTrue();
        assertThat(parking.parkTransport(trailerTruck)).isTrue();
    }

    @Test
    public void whenPlacesNotEnoughThenFalseForTrailerTruck() {
        CommonParking parking = new CommonParking(1, 1);
        Transport car = new Car();
        Transport truck = new Truck();
        Transport trailerTruck = new TrailerTruck(truck);
        assertThat(parking.parkTransport(car)).isTrue();
        assertThat(parking.parkTransport(truck)).isTrue();
        assertThat(parking.parkTransport(trailerTruck)).isFalse();
    }

    @Test
    public void whenTruckPlacesNotEnoughButCarPlacesEnoughThenAllTrue() {
        CommonParking parking = new CommonParking(5, 1);
        Transport car = new Car();
        Transport truck = new Truck();
        Transport trailerTruck = new TrailerTruck(truck);
        assertThat(parking.parkTransport(car)).isTrue();
        assertThat(parking.parkTransport(truck)).isTrue();
        assertThat(parking.parkTransport(trailerTruck)).isTrue();
    }
    @Test
    public void whenTruckPlacesEnoughButCarPlacesNotEnoughThanFalseForOtherCar() {
        CommonParking parking = new CommonParking(1, 3);
        Transport car = new Car();
        Transport otherCar = new Car();
        Transport trailerTruck = new Truck();
        assertThat(parking.parkTransport(car)).isTrue();
        assertThat(parking.parkTransport(otherCar)).isFalse();
        assertThat(parking.parkTransport(trailerTruck)).isTrue();
    }
}