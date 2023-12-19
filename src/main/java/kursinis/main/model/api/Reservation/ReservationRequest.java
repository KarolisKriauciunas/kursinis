package kursinis.main.model.api.Reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ReservationRequest {
    @Schema(description = "reservation start date", example = "2023-10-10")
    private Date reservationStartDate;
    @Schema(description = "reservation end date", example = "2023-10-12")
    private Date reservationEndDate;
    @Schema(description = "parking space id", example = "1")
    private Long parkingSpaceId;
    @Schema(description = "user id", example = "1")
    private Long userId;
}
