package kursinis.main.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CargoResponse {
    @Schema(description = "cargo description", example = "something very very expensive")
    private String description;
    @Schema(description = "cargo value", example = "12.00")
    private float value;
    @Schema(description = "cargo ID", example = "1")
    private Long id;
}
