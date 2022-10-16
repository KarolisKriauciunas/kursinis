package kursinis.main.model.domain;

import kursinis.main.model.domain.Account.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Vehicles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String manufacturer;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long vehicleID;
    private String creationYear;
    private Float Value;
    private Long completedTrips;
    @Column(name = "TYPE", columnDefinition = "bigint default 0", nullable = false)
    private VehicleType type;
    private String lastService;
    @ManyToOne
    @JoinColumn(name = "assignedID")
    private User assignedId;
}

