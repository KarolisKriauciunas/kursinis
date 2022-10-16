package kursinis.main.repository;

import kursinis.main.model.domain.Trip.Cargo;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    List<Cargo> findAllById(Long Id);
    Cargo findCargoById(Long Id);
}
