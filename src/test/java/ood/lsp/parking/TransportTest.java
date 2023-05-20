package ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TransportTest {
    @Test
    public void whenCarGetSize() {
        Transport car = new Car();
        int expected = 1;
        assertThat(car.getSize()).isEqualTo(expected);
    }

    @Test
    public void whenTruckGetSize() {
        Transport truck = new Truck();
        int expected = 2;
        assertThat(truck.getSize()).isEqualTo(expected);
    }

    @Test
    public void whenTrailerTruckGetSize() {
        Transport truck = new Truck();
        Transport trailerTruck = new TrailerTruck(truck);
        int expected = 4;
        assertThat(trailerTruck.getSize()).isEqualTo(expected);
    }
}