package kursinis.main.model.api.Cargo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CargoRequest {

    @Schema(description = "cargo description", example = "something very very expensive")
    private String description;
    @Schema(description = "cargo value", example = "12.00")
    private float value;
}
