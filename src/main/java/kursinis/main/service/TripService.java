package kursinis.main.service;

import kursinis.main.model.api.TripRequest;
import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Trip.Cargo;
import kursinis.main.model.domain.Trip.Trip;
import kursinis.main.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final CargoService cargoService;
    private final UserService userService;
    TripRepository tripRepository;

    @Autowired
    TripService(TripRepository tripRepository, CargoService cargoService, UserService userService)
    {
        this.tripRepository = tripRepository;
        this.cargoService = cargoService;
        this.userService = userService;
    }
    public List<Trip> fetchTrips(Long TripId) {
        if(TripId != null)return tripRepository.findAllByTripID(TripId);
        return tripRepository.findAll();
    }
    public List<Trip> fetchDriverTrips(Long driverId) {
        if(driverId != null)return tripRepository.findAllByDriver(driverId);
        return tripRepository.findAll();
    }
    public Trip createTrip(TripRequest request) {
        User user = userService.fetchUser(request.getDriverID());
        Cargo cargo = cargoService.fetchCargo(request.getMerchandiseID());
        Trip trip = Trip.builder()
                .driver(user)
                .destination(request.getDestination())
                .tripStartDate(null)
                .tripEndDate(null)
                .merchandiseID(cargo)
                .build();

        return tripRepository.save(trip);
    }

    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
