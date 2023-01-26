package kursinis.main.model.api.Vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {
    @Schema()
    private String manufacturer;
    @Schema()
    private Long vehicleID;
    @Schema()
    private String creationYear;
    @Schema()
    private Float Value;
    @Schema()
    private Long completedTrips;
    @Schema()
    private VehicleType type;
    @Schema()
    private String lastService;
    @Schema()
    private Long assignedId;
}
