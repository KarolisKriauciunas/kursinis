package kursinis.main.model.api.Trip;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripRequest {
    @Schema(description = "assigned DriverID", example = "1")
    private Long driverID;
    @Schema(description = "trip start date", example = "2022-10-16")
    private Timestamp tripStartDate;
    @Schema(description = "trip end date", example = "null")
    private Timestamp tripEndDate;
    @Schema(description = "trip description", example = "very long trip")
    private String destination;
    @Schema(description = "assigned cargo id", example = "1")
    private Long merchandiseID;
    @Schema(description = "trip ID")
    private Long tripID;
}
