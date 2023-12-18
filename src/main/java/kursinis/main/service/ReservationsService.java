package kursinis.main.service;

import kursinis.main.model.api.ReservationRequest;
import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Reservation;
import kursinis.main.model.domain.Trip.ParkingSpace;
import kursinis.main.model.domain.Trip.ReservationStatus;
import kursinis.main.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsService {

    private ReservationRepository repository;
    private ParkingSpaceService parkingSpaceService;
    private UserService userService;

    public Reservation createReservation(ReservationRequest request) {
        Optional<ParkingSpace> parkingSpace = parkingSpaceService.fetchParkingSpace(request.getParkingSpaceId());
        Optional<User> user = userService.fetchUser(request.getUserId());

        if(parkingSpace.isPresent() && user.isPresent()) {
            return repository.save(Reservation.builder()
                    .reservationStartDate(request.getReservationStartDate())
                    .reservationEndDate(request.getReservationEndDate())
                    .parkingSpaceId(parkingSpace.get())
                    .userId(user.get())
                    .status(ReservationStatus.UPCOMING)
                    .build());
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided information is faulty");
    }

}
