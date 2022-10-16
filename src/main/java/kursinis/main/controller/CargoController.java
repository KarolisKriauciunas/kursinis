package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import kursinis.main.model.api.CargoResponse;
import kursinis.main.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class CargoController {
    private final CargoService cargoService;
    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }
    @GetMapping(value = "/cargos")
    public List<CargoResponse> fetchCargos(@RequestParam(required = false) Long id) {
        return cargoService.fetchCargos(id).stream()
                .map(p -> new CargoResponse(p.getDescription(),p.getValue(),p.getId()))
                .collect(Collectors.toList());
    }
    @DeleteMapping(value = "/cargos/{cargoId}")
    @Operation(summary = "Delete cargo from database")
    public ResponseEntity<Void> deleteCargo(@PathVariable Long cargoId){
        cargoService.deleteCargo(cargoId);
        return ResponseEntity.noContent().build();
    }
}
