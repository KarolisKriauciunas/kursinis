package kursinis.main.model.api.TripStop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripStopResponse {
    @Schema()
    private Long tripID;
    @Schema()
    private Long stopID;
    @Schema()
    private String description;
    @Schema()
    private String stopAddress;
}
