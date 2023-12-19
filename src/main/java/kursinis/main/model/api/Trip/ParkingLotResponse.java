package kursinis.main.model.api.Trip;

import kursinis.main.model.domain.Trip.City;
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
