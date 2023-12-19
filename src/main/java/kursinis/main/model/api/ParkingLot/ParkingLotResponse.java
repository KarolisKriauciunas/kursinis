package kursinis.main.model.api.ParkingLot;

import kursinis.main.model.domain.Parking.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ParkingLotResponse {
    private Long parkingLotId;
    private City city;
    private String address;
}
