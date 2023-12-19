package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.Trip.ParkingLotRequest;
import kursinis.main.model.api.Trip.ParkingLotResponse;
import kursinis.main.model.api.TripStop.ParkingSpaceResponse;
import kursinis.main.service.ParkingLotService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/parkinglot")
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    @PostMapping(path = "/create")
    @Operation(summary = "Create Trip instance in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New Trip successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createParkingLot(@Validated @RequestBody ParkingLotRequest request) {
        return parkingLotService.createParkingLot(request).getParkingLotId();
    }

    @GetMapping()
    @Operation(summary = "Get specific Trip by ID or all if no ID provided")
    public List<ParkingLotResponse> fetchParkingLots() {
        return parkingLotService.fetchAllParkingLots().stream()
                .map(p -> new ParkingLotResponse(p.getParkingLotId(), p.getCity(), p.getAddress()))
                .toList();
    }
//    @GetMapping(value = "/{driverID}/trips")
//    @Operation(summary = "Get specific Trips  that are assigned to an employee")
//    public List<TripResponse> fetchDriverTrips(@PathVariable Long driverID) {
//       List<ParkingLot> temp =  parkingLotService.fetchTrips(null);
//        temp.removeIf(trip -> (!Objects.equals(trip.getDriver().getEmployeeID(), driverID)));
//
//        return temp.stream()
//                .map(p -> new TripResponse(p.getDriver().getEmployeeID(), p.getTripStartDate(),
//                        p.getTripEndDate(), p.getDestination(), p.getTripID(), p.getMerchandiseID().getId(),p.getStatus()))
//                .collect(Collectors.toList());
//    }
//    @DeleteMapping(value = "/trips/{tripId}")
//    @Operation(summary = "Delete Trip from database")
//    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
//        parkingLotService.deleteTrip(tripId);
//        return ResponseEntity.noContent().build();
//    }
//    @PutMapping("/update/trip/{id}")
//    @Operation(summary = "Update a trip info in database")
//    ResponseEntity<Void> replaceUser(@PathVariable Long id, @RequestParam(required = false) String description, @RequestParam(required = false) String endDate) {
//        parkingLotService.updateTrip(id, description, endDate);
//        return ResponseEntity.ok().build();
//    }
}
