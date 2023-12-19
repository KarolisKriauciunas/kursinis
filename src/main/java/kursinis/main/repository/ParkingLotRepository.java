package kursinis.main.repository;

import kursinis.main.model.domain.Trip.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
