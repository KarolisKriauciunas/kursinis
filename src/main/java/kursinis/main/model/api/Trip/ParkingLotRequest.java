package kursinis.main.model.api.Trip;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.Trip.City;
import kursinis.main.model.domain.Trip.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ParkingLotRequest {
    @Schema(description = "Parking lot address", example = "Laisves al. 5")
    private String address;
    @Schema(description = "Parking lot city", example = "KAUNAS")
    private City city;
}
