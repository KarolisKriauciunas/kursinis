package kursinis.main.model.api.TripStop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpaceRequest {
    @Schema(description = "Parking space name", example = "-2a. 64")
    private String name;
    @Schema(description = "Parking space daily price", example = "1")
    private float price;
    @Schema(description = "Parking lot id", example = "1")
    private Long parkingLotId;
}
