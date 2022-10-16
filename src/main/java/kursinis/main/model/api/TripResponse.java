package kursinis.main.model.api;

import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Trip.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TripResponse {
    private User Driver;
    private Timestamp tripStartDate;
    private Timestamp tripEndDate;
    private String destination;
    private Long tripID;
    private Cargo merchandiseID;
}
