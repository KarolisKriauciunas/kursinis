package kursinis.main.repository;

import kursinis.main.model.domain.Trip.TripStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripStopRepository extends JpaRepository<TripStop, Long> {
}
