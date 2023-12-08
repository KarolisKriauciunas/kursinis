package kursinis.main.model.api.Vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleRequest {
    @Schema()
    private String manufacturer;
    @Schema()
    private String creationYear;
    @Schema()
    private String Value;
    @Schema()
    private Long completedTrips;
    @Schema(description = "Vehicle type", example = "0")
    private VehicleType type;
    @Schema()
    private String lastService;
    @Schema()
    private Long assignedId;
}
