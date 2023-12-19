package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.ReservationRequest;
import kursinis.main.model.api.Trip.ParkingSpaceResponse;
import kursinis.main.model.api.TripStop.ParkingSpaceRequest;
import kursinis.main.service.ReservationsService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/reservations")
public class ReservationsController {

    private ReservationsService service;
    @PostMapping(path = "/create")
    @ApiResponse(
            responseCode = "201",
            description = "New TripStop successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createReservation(@Validated @RequestBody ReservationRequest request) {
        return service.createReservation(request).getReservationId();
    }
}
