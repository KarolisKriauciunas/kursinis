package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.Reservation.ReservationRequest;
import kursinis.main.model.api.Reservation.ReservationResponse;
import kursinis.main.model.domain.Parking.ReservationStatus;
import kursinis.main.service.ReservationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update/reservation/{id}")
    @Operation(summary = "Update reservation info in database")
    ResponseEntity<Void> editReservation(@PathVariable Long id, ReservationStatus status) {
        service.updateReservation(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<ReservationResponse> fetchReservations() {
        return service.fetchAllReservations().stream()
                .map(p -> new ReservationResponse(p.getReservationId(), p.getReservationStartDate(), p.getReservationEndDate(), p.getParkingSpaceId().getParkingSpaceId(), p.getUserId().getUserId(), p.getReservationStatus()))
                .toList();
    }
}
