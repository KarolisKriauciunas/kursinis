package kursinis.main.service;

import kursinis.main.model.api.ParkingSpace.ParkingSpaceRequest;
import kursinis.main.model.domain.Parking.ParkingLot;
import kursinis.main.model.domain.Parking.ParkingSpace;
import kursinis.main.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingLotService parkingLotService;

    @Autowired
    ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository, ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public ParkingSpace createParkingSpace(ParkingSpaceRequest request) {
        Optional<ParkingLot> parkingLot = parkingLotService.fetchParkingLot(request.getParkingLotId());

        if (parkingLot.isPresent()) {
            return parkingSpaceRepository.save(ParkingSpace.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .parkingLotID(parkingLot.get())
                    .build());
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such parking lot exists");
    }

    public List<ParkingSpace> fetchParkingSpacesByParkingLot(Long parkingLotId) {
        return parkingSpaceRepository.findAll()
                .stream()
                .filter(space -> parkingLotId.equals(space.getParkingLotID().getParkingLotId()))
                .toList();
    }

    public Optional<ParkingSpace> fetchParkingSpace(Long id) {
        return parkingSpaceRepository.findById(id);
    }

    public void deleteTripStop(Long stopId) {
        parkingSpaceRepository.deleteById(stopId);
    }
}
