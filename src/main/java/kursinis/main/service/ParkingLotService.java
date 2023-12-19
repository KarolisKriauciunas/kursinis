package kursinis.main.service;

import kursinis.main.model.api.Trip.ParkingLotRequest;
import kursinis.main.model.api.Trip.ParkingLotResponse;
import kursinis.main.model.domain.Trip.ParkingLot;
import kursinis.main.repository.ParkingLotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> fetchAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    public Optional<ParkingLot> fetchParkingLot(Long parkingLotId) {
        return parkingLotRepository.findById(parkingLotId);
    }

    public ParkingLot createParkingLot(ParkingLotRequest request) {
        return parkingLotRepository.save(ParkingLot.builder()
                .address(request.getAddress())
                .city(request.getCity())
                .build());
    }

    public void deleteTrip(Long tripId) {
        parkingLotRepository.deleteById(tripId);
    }
}
