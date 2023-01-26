package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.Trip.TripRequest;
import kursinis.main.model.api.Trip.TripResponse;
import kursinis.main.model.domain.Trip.Trip;
import kursinis.main.service.CargoService;
import kursinis.main.service.TripService;
import kursinis.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class TripController {
    private final TripService tripService;
    private final CargoService cargoService;
    private final UserService userService;

    @Autowired
    TripController(TripService tripService, CargoService cargoService, UserService userService) {
        this.tripService = tripService;
        this.cargoService = cargoService;
        this.userService = userService;
    }

    @PostMapping(path = "/createtrip")
    @Operation(summary = "Create Trip instance in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New Trip successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createTrip(@Validated @RequestBody TripRequest request) {

        return tripService.createTrip(request).getTripID();
    }

    @GetMapping(value = "/trips")
    @Operation(summary = "Get specific Trip by ID or all if no ID provided")
    public List<TripResponse> fetchTrips(@RequestParam(required = false) Long tripId) {
        return tripService.fetchTrips(tripId).stream()
                .map(p -> new TripResponse(p.getDriver().getEmployeeID(), p.getTripStartDate(),
                        p.getTripEndDate(), p.getDestination(), p.getTripID(), p.getMerchandiseID().getId()))
                .collect(Collectors.toList());
    }
    @GetMapping(value = "/{driverID}/trips")
    @Operation(summary = "Get specific Trips  that are assigned to an employee")
    public List<TripResponse> fetchDriverTrips(@PathVariable Long driverID) {
       List<Trip> temp =  tripService.fetchTrips(null);
        temp.removeIf(trip -> (!Objects.equals(trip.getDriver().getEmployeeID(), driverID)));

        return temp.stream()
                .map(p -> new TripResponse(p.getDriver().getEmployeeID(), p.getTripStartDate(),
                        p.getTripEndDate(), p.getDestination(), p.getTripID(), p.getMerchandiseID().getId()))
                .collect(Collectors.toList());
    }
    @DeleteMapping(value = "/trips/{tripId}")
    @Operation(summary = "Delete Trip from database")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/trip/{id}")
    @Operation(summary = "Update a trip info in database")
    ResponseEntity<Void> replaceUser(@PathVariable Long id, @RequestParam(required = false) String description, @RequestParam(required = false) String endDate) {
        tripService.updateTrip(id, description, endDate);
        return ResponseEntity.ok().build();
    }
}
