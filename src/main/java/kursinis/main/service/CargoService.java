package kursinis.main.service;

import kursinis.main.model.api.CargoRequest;
import kursinis.main.model.domain.Trip.Cargo;
import kursinis.main.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> fetchCargos(Long id) {
        if (id != null) return cargoRepository.findAllById(id);
        return cargoRepository.findAll();
    }

    public Cargo fetchCargo(Long Id) {
        return cargoRepository.findCargoById(Id);
    }

    public Cargo createCargo(CargoRequest request) {
        Cargo cargo = Cargo.builder()
                .description(request.getDescription())
                .value(request.getValue())
                .build();

        return cargoRepository.save(cargo);
    }

    public void deleteCargo(Long CargoId) {
        cargoRepository.deleteById(CargoId);
    }

    public void updateCargo(Long id, String description, Float value) {
        Cargo cargo = fetchCargo(id);
        if (cargo != null) {
            if (description != null) cargo.setDescription(description);
            if (value != null) cargo.setValue(value);
            cargoRepository.save(cargo);
        }
    }
}
