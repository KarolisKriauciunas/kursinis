package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.ParkingSpace.ParkingSpaceRequest;
import kursinis.main.model.api.ParkingSpace.ParkingSpaceResponse;
import kursinis.main.service.ParkingSpaceService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/parkingspace")
public class ParkingSpaceController {
    private ParkingSpaceService parkingSpaceService;

    @PostMapping(path = "/create")
    @Operation(summary = "Create TripStop in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New TripStop successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createTrip(@Validated @RequestBody ParkingSpaceRequest request) {

        return parkingSpaceService.createParkingSpace(request).getParkingSpaceId();
    }

    @GetMapping(value = "/parkingspaces")
    public List<ParkingSpaceResponse> fetchParkingSpacesByParkingLot(@RequestParam Long parkingLotId) {
        return parkingSpaceService.fetchParkingSpacesByParkingLot(parkingLotId).stream()
                .map(p -> new ParkingSpaceResponse(p.getParkingSpaceId(), p.getName(), p.getPrice(), p.getParkingLotID().getParkingLotId()))
                .collect(Collectors.toList());
    }

//
//    @GetMapping(value = "/{TripID}/tripstops")
//    @Operation(summary = "Get specific TripStops assigned to a Trip by Trip ID")
//    public List<TripStopResponse> fetchSpecificTripStops(@PathVariable Long TripID) {
//        List<ParkingSpace> temp = parkingSpaceService.fetchTripStops(null);
//        temp.removeIf(tripStop -> (tripStop.getParkingLotID().getTripID() !=TripID ));
//        return temp.stream()
//                .map(p -> new TripStopResponse(p.getParkingLotID().getTripID(),p.getStopID(),p.getDescription(),p.getStopAddress()))
//                .collect(Collectors.toList());
//    }
//
//    @DeleteMapping(value = "/tripstop/{stopId}")
//    @Operation(summary = "Delete TripStop from database")
//    public ResponseEntity<Void> deleteTrip(@PathVariable Long stopId) {
//        parkingSpaceService.deleteTripStop(stopId);
//        return ResponseEntity.noContent().build();
//    }
}
