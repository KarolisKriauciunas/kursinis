package kursinis.main.repository;

import kursinis.main.model.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByVehicleID(Long Id);
    List<Vehicle> findAllByManufacturer(String manufacturer);
    List<Vehicle> findAllByAssignedId(Long Id);
}
