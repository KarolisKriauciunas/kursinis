package kursinis.main.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripStopRequest {
    @Schema()
    private Long tripID;
    @Schema()
    private Long stopID;
    @Schema()
    private String description;
    @Schema()
    private String stopAddress;
}
