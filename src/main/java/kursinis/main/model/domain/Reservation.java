package kursinis.main.model.domain;

import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Trip.ParkingSpace;
import kursinis.main.model.domain.Trip.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Reservations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Date reservationStartDate;
    private Date reservationEndDate;
    private ReservationStatus reservationStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "parkingSpaceId", nullable = false)
    private ParkingSpace parkingSpaceId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;
}
