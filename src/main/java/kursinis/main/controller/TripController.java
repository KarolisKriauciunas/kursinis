package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.TripRequest;
import kursinis.main.model.api.TripResponse;
import kursinis.main.model.domain.Account.User;
import kursinis.main.service.CargoService;
import kursinis.main.service.TripService;
import kursinis.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class TripController {
    private final TripService tripService;
    private final CargoService cargoService;
    private final UserService userService;

    @Autowired
    TripController(TripService tripService, CargoService cargoService, UserService userService)
    {
        this.tripService = tripService;
        this.cargoService = cargoService;
        this.userService = userService;
    }
    @PostMapping(path = "/createttrip")
    @Operation(summary = "Create new instance in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New Trip successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createUser(@Validated @RequestBody TripRequest request) {

        return tripService.createTrip(request).getTripID();
    }
    @GetMapping(value = "/trips")
    public List<TripResponse> fetchTrips(@RequestParam(required = false) Long tripId) {
        return tripService.fetchTrips(tripId).stream()
                .map(p -> new TripResponse(p.getDriver(),p.getTripStartDate(),p.getTripEndDate(),p.getDestination(),p.getTripID(),p.getMerchandiseID()))
                .collect(Collectors.toList());
    }
    @GetMapping(value = "/{driversID}/trips")
    public List<TripResponse> fetchDriversTrips(@PathVariable Long driversID) {
        return tripService.fetchDriverTrips(driversID).stream()
                .map(p -> new TripResponse(p.getDriver(),p.getTripStartDate(),p.getTripEndDate(),p.getDestination(),p.getTripID(),p.getMerchandiseID()))
                .collect(Collectors.toList());
    }
    @DeleteMapping(value = "/trips/{tripId}")
    @Operation(summary = "Delete User from database")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId){
        tripService.deleteTrip(tripId);
        return ResponseEntity.noContent().build();
    }
}
