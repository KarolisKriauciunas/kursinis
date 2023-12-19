package kursinis.main.model.api.ParkingLot;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.Parking.City;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingLotRequest {
    @Schema(description = "Parking lot address", example = "Laisves al. 5")
    private String address;
    @Schema(description = "Parking lot city", example = "KAUNAS")
    private City city;
}
