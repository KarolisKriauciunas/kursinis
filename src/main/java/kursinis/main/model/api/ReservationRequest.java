package kursinis.main.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ReservationRequest {
    @Schema(description = "reservation start date", example = "2023-10-10")
    private Timestamp reservationStartDate;
    @Schema(description = "reservation end date", example = "2023-10-12")
    private Timestamp reservationEndDate;
    @Schema(description = "parking space id", example = "1")
    private Long parkingSpaceId;
    @Schema(description = "user id", example = "1")
    private Long userId;
}
