package kursinis.main.service;

import kursinis.main.model.domain.Account.User;
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
        if(id != null)return cargoRepository.findAllById(id);
        return cargoRepository.findAll();
    }
    public Cargo fetchCargo(Long Id)
    {
        return cargoRepository.findCargoById(Id);
    }
    public void deleteCargo(Long CargoId){
        cargoRepository.deleteById(CargoId);
    }
}
