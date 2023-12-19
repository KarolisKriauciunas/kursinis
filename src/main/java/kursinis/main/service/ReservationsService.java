package kursinis.main.service;

import kursinis.main.model.api.ReservationRequest;
import kursinis.main.model.api.ReservationResponse;
import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Reservation;
import kursinis.main.model.domain.Trip.ParkingLot;
import kursinis.main.model.domain.Trip.ParkingSpace;
import kursinis.main.model.domain.Trip.ReservationStatus;
import kursinis.main.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsService {

    private ReservationRepository repository;
    private ParkingSpaceService parkingSpaceService;
    private ParkingLotService parkingLotService;
    private UserService userService;

    public List<ReservationResponse> fetchAllReservations() {
        List<Reservation> reservations = repository.findAll();
        List<ReservationResponse> reservationResponseList = new ArrayList<>();

        for (Reservation res: reservations) {
            Optional<ParkingSpace> parkingSpace = parkingSpaceService.fetchParkingSpace(res.getParkingSpaceId().getParkingSpaceId());
            Optional<ParkingLot> parkingLot = parkingLotService.fetchParkingLot(res.getParkingSpaceId().getParkingLotID().getParkingLotId());
            reservationResponseList.add(
                    ReservationResponse.builder()
                            .reservationStartDate(res.getReservationStartDate())
                            .reservationEndDate(res.getReservationEndDate())
                            .userId(res.getUserId().getUserId())
                            .reservationStatus(res.getReservationStatus())
                            .parkingSpaceName(parkingSpace.get().getName())
                            .city(parkingLot.get().getCity())
                            .address(parkingLot.get().getAddress())
                            .price((Duration.between(res.getReservationStartDate().toLocalDate().atStartOfDay(), res.getReservationEndDate().toLocalDate().atStartOfDay()).toDays() + 1) * parkingSpace.get().getPrice())
                            .build()
            );
        }
        return reservationResponseList;
    }

    public Reservation createReservation(ReservationRequest request) {
        Optional<ParkingSpace> parkingSpace = parkingSpaceService.fetchParkingSpace(request.getParkingSpaceId());
        Optional<User> user = userService.fetchUser(request.getUserId());

        if(parkingSpace.isPresent() && user.isPresent()) {
            return repository.save(Reservation.builder()
                    .reservationStartDate(request.getReservationStartDate())
                    .reservationEndDate(request.getReservationEndDate())
                    .parkingSpaceId(parkingSpace.get())
                    .userId(user.get())
                    .reservationStatus(ReservationStatus.UPCOMING)
                    .build());
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided information is faulty");
    }

}
