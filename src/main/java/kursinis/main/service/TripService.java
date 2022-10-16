package kursinis.main.service;

import kursinis.main.model.api.TripRequest;
import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Trip.Cargo;
import kursinis.main.model.domain.Trip.Trip;
import kursinis.main.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final CargoService cargoService;
    private final UserService userService;
    private final TripRepository tripRepository;

    @Autowired
    TripService(TripRepository tripRepository, CargoService cargoService, UserService userService)
    {
        this.tripRepository = tripRepository;
        this.cargoService = cargoService;
        this.userService = userService;
    }

    public void updateTrip(Long id, String description, String endDate) {
        Trip trip = fetchTrip(id);
        if(description != null)trip.setDestination(description);
        if(endDate != null)trip.setTripEndDate(Timestamp.valueOf(endDate));
        tripRepository.save(trip);
    }

    public List<Trip> fetchTrips(Long TripId) {
        if(TripId != null)return tripRepository.findAllByTripID(TripId);
        return tripRepository.findAll();
    }
    public Trip fetchTrip(Long TripId) {
        return tripRepository.findTripByTripID(TripId);
    }
    public List<Trip> fetchDriverTrips(Long driverId) {
        return tripRepository.findAll();
    }
    public Trip createTrip(TripRequest request) {
        Optional<User> user = userService.fetchUser(request.getDriverID());
        Cargo cargo = cargoService.fetchCargo(request.getMerchandiseID());
        Trip trip = Trip.builder()
                .driver(user.get())
                .destination(request.getDestination())
                .tripStartDate(request.getTripStartDate())
                .tripEndDate(request.getTripEndDate())
                .merchandiseID(cargo)
                .build();

        return tripRepository.save(trip);
    }

    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
