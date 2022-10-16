package kursinis.main.service;

import kursinis.main.model.api.VehicleRequest;
import kursinis.main.model.domain.Account.User;
import kursinis.main.model.domain.Vehicle;
import kursinis.main.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Vehicle> fetchSpecificManufacturerVehicles(String manufacturer) {
        return vehicleRepository.findAllByManufacturer(manufacturer);
    }

    public List<Vehicle> fetchDriverVehicles(Long driverId) {
        return vehicleRepository.findAllByAssignedId(driverId);
    }

    public Vehicle createVehicle(VehicleRequest request) {
        Optional<User> user = userService.fetchUser(request.getAssignedId());
        Vehicle vehicle = Vehicle.builder()
                .assignedId(user.get())
                .completedTrips(request.getCompletedTrips())
                .creationYear(request.getCreationYear())
                .lastService(request.getLastService())
                .manufacturer(request.getManufacturer())
                .type(request.getType())
                .Value(request.getValue())
                .build();

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long VehicleId) {
        vehicleRepository.deleteById(VehicleId);
    }
}
