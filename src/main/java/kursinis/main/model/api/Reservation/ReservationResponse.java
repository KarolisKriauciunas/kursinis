package kursinis.main.model.api.Reservation;

import kursinis.main.model.domain.Parking.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ReservationResponse {
    private Long reservationId;
    private Date reservationStartDate;
    private Date reservationEndDate;
    private Long parkingSpaceId;
    private Long userId;
    private ReservationStatus reservationStatus;
}
