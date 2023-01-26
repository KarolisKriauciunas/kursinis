package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.Vehicle.VehicleRequest;
import kursinis.main.model.api.Vehicle.VehicleResponse;
import kursinis.main.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping(path = "/createvehicle")
    @Operation(summary = "Create Vehicle in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New vehicle successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createVehicle(@Validated @RequestBody VehicleRequest request) {
        return vehicleService.createVehicle(request).getVehicleID();
    }
    @GetMapping(value = "/vehicles")
    @Operation(summary = "Get specific Vehicle by VehicleID or all if no ID provided")
    public List<VehicleResponse> fetchVehicles(@RequestParam(required = false) Long vehicleID) {
        return vehicleService.fetchVehicles(vehicleID).stream()
                .map(p -> new VehicleResponse(p.getManufacturer(),p.getVehicleID(),p.getCreationYear(),p.getValue(),p.getCompletedTrips(),p.getType(),p.getLastService(),p.getAssignedId().getEmployeeID()))
                .collect(Collectors.toList());
    }
    @GetMapping(value = "/{manufacturer}/vehicles")
    @Operation(summary = "Get specific manufacturer vehicles")
    public List<VehicleResponse> fetchVehicles(@PathVariable String manufacturer) {
        return vehicleService.fetchSpecificManufacturerVehicles(manufacturer).stream()
                .map(p -> new VehicleResponse(p.getManufacturer(),p.getVehicleID(),p.getCreationYear(),p.getValue(),p.getCompletedTrips(),p.getType(),p.getLastService(),p.getAssignedId().getEmployeeID()))
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/vehicle/{vehicleID}")
    @Operation(summary = "Delete Vehicle from database")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleID){
        vehicleService.deleteVehicle(vehicleID);
        return ResponseEntity.noContent().build();
    }
}
