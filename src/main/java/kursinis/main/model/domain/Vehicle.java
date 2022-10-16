package kursinis.main.model.domain;

import kursinis.main.model.domain.Account.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Vehicles")
public class Vehicle {
    private String manufacturer;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long vehicleID;
    private String creationYear;
    private Float Value;
    private Long completedTrips;
    private VehicleType type;
    private String lastService;
    @OneToOne
    @JoinColumn(name = "assignedID")
    private User assignedId;
}

