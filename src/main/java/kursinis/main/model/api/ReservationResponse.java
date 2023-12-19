package kursinis.main.model.api;

import kursinis.main.model.domain.Trip.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ReservationResponse {
    private Date reservationStartDate;
    private Date reservationEndDate;
    private Long parkingSpaceId;
    private Long userId;
    private ReservationStatus reservationStatus;
}
