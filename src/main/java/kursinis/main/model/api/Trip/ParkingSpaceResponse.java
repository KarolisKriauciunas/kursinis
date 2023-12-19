package kursinis.main.model.api.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpaceResponse {
    private Long parkingLotId;
    private Long parkingSpaceId;
    private String name;
}
