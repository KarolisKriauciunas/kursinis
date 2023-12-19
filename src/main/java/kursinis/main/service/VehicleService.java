package kursinis.main.service;

import kursinis.main.model.api.Vehicle.VehicleRequest;
import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Vehicle;
import kursinis.main.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserService userService;

    @Autowired
    VehicleService(VehicleRepository vehicleRepository, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
    }

    public List<Vehicle> fetchVehicles(Long Id) {
        if (Id != null) return vehicleRepository.findAllByVehicleID(Id);
        return vehicleRepository.findAll();
    }

    public List<Vehicle> fetchUserVehicles(Long userId) {
        return vehicleRepository.findAll()
                .stream()
                .filter(vehicle -> userId.equals(vehicle.getAssignedId().getUserId()))
                .toList();
    }

    public List<Vehicle> fetchDriverVehicles(Long driverId) {
        return vehicleRepository.findAllByAssignedId(driverId);
    }

    public Vehicle createVehicle(VehicleRequest request) {
        if(fetchUserVehicles(request.getAssignedId()).size() == 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has 2 vehicles registered");
        }

        Optional<User> user = userService.fetchUser(request.getAssignedId());
        Vehicle vehicle = Vehicle.builder()
                .assignedId(user.get())
                .plateNumbers(request.getPlateNumbers())
                .carName(request.getCarName())
                .build();

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long VehicleId) {
        vehicleRepository.deleteById(VehicleId);
    }
}
