package kursinis.main.model.api;

import kursinis.main.model.domain.Trip.City;
import kursinis.main.model.domain.Trip.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
public class ReservationResponse {
    private Long reservationId;
    private Date reservationStartDate;
    private Date reservationEndDate;
    private String parkingSpaceName;
    private Long userId;
    private ReservationStatus reservationStatus;
    private City city;
    private String address;
    private float price;
}
