package kursinis.main.repository;

import kursinis.main.model.domain.Trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByDriver(Long driverId);
    List<Trip>findAllByTripID(Long TripId);
}
