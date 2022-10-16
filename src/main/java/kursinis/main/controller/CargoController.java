package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.CargoRequest;
import kursinis.main.model.api.CargoResponse;
import kursinis.main.model.api.CreateUserRequest;
import kursinis.main.model.domain.Trip.Cargo;
import kursinis.main.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    @Operation(summary = "Get specific Cargos or all if no ID provided")
    public List<CargoResponse> fetchCargos(@RequestParam(required = false) Long id) {
        return cargoService.fetchCargos(id).stream()
                .map(p -> new CargoResponse(p.getDescription(),p.getValue(),p.getId()))
                .collect(Collectors.toList());
    }
    @PostMapping(path = "/createcargo")
    @Operation(summary = "Create new Cargo in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New cargo successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createCargo(@Validated @RequestBody CargoRequest request) {
        return cargoService.createCargo(request).getId();
    }
    @DeleteMapping(value = "/cargos/{cargoId}")
    @Operation(summary = "Delete Cargo from database")
    public ResponseEntity<Void> deleteCargo(@PathVariable Long cargoId){
        cargoService.deleteCargo(cargoId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/cargo/{id}")
    @Operation(summary = "Update a cargo info in database")
    ResponseEntity<Void> replaceUser(@PathVariable Long id, @RequestParam(required = false) String description, @RequestParam(required = false) Float value) {
        cargoService.updateCargo(id, description, value);
        return ResponseEntity.ok().build();
    }
}
