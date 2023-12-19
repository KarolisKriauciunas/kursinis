package kursinis.main.repository;

import kursinis.main.model.domain.Trip.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}
