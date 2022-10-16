package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.TripStopRequest;
import kursinis.main.model.api.TripStopResponse;
import kursinis.main.model.domain.Trip.TripStop;
import kursinis.main.service.TripStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class TripStopController {
    private TripStopService tripStopService;

    @Autowired
    TripStopController(TripStopService tripStopService)
    {
        this.tripStopService = tripStopService;
    }

    @GetMapping(value = "/tripstops")
    @Operation(summary = "Get specific TripStops by Id or all if no ID provided")
    public List<TripStopResponse> fetchTripStops(@RequestParam(required = false) Long stopId) {
        return tripStopService.fetchTripStops(stopId).stream()
                .map(p -> new TripStopResponse(p.getTripID().getTripID(),p.getStopID(),p.getDescription(),p.getStopAddress()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{TripID}/tripstops")
    @Operation(summary = "Get specific TripStops assigned to a Trip by Trip ID")
    public List<TripStopResponse> fetchSpecificTripStops(@PathVariable Long TripID) {
        List<TripStop> temp = tripStopService.fetchTripStops(null);
        temp.removeIf(tripStop -> (tripStop.getTripID().getTripID() !=TripID ));
        return temp.stream()
                .map(p -> new TripStopResponse(p.getTripID().getTripID(),p.getStopID(),p.getDescription(),p.getStopAddress()))
                .collect(Collectors.toList());
    }
    @PostMapping(path = "/createtripstop")
    @Operation(summary = "Create TripStop in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New TripStop successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createTrip(@Validated @RequestBody TripStopRequest request) {

        return tripStopService.createTripStop(request).getStopID();
    }
    @DeleteMapping(value = "/tripstop/{stopId}")
    @Operation(summary = "Delete TripStop from database")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long stopId) {
        tripStopService.deleteTripStop(stopId);
        return ResponseEntity.noContent().build();
    }
}
