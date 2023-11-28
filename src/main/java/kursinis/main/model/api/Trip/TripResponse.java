package kursinis.main.model.api.Trip;

import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Trip.Cargo;
import kursinis.main.model.domain.Trip.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripResponse {
    private Long driverId;
    private Timestamp tripStartDate;
    private Timestamp tripEndDate;
    private String destination;
    private Long tripId;
    private Long cargoId;
    private TripStatus status;
}
