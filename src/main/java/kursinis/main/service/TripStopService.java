package kursinis.main.service;

import kursinis.main.model.api.TripStopRequest;
import kursinis.main.model.domain.Trip.Trip;
import kursinis.main.model.domain.Trip.TripStop;
import kursinis.main.repository.TripStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripStopService {
    private final TripStopRepository tripStopRepository;
    private final TripService tripService;

    @Autowired
    TripStopService(TripStopRepository tripStopRepository,TripService tripService)
    {
        this.tripService = tripService;
        this.tripStopRepository = tripStopRepository;
    }

    public List<TripStop> fetchTripStops(Long stopId) {
        if(stopId != null)return tripStopRepository.findAllByStopID(stopId);
        return tripStopRepository.findAll();
    }

    public TripStop createTripStop(TripStopRequest request) {

        Trip trip  = tripService.fetchTrip(request.getTripID());

        TripStop tripStop = TripStop.builder()
                .stopID(request.getStopID())
                .stopAddress(request.getStopAddress())
                .description(request.getDescription())
                .tripID(trip)
                .build();

        return tripStopRepository.save(tripStop);
    }
    public void deleteTripStop(Long stopId) {
        tripStopRepository.deleteById(stopId);
    }
}
