package kursinis.main.model.api.TripStop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpaceResponse {
    private Long parkingLotId;
    private String name;
    private float price;
    private Long parkingSpaceId;
}
