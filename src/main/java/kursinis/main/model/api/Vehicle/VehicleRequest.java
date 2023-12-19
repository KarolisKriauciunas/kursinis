package kursinis.main.model.api.Vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleRequest {
    @Schema()
    private String carName;
    @Schema()
    private String plateNumbers;
    @Schema()
    private Long assignedId;
}
