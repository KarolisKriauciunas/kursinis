package kursinis.main.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String Value;
    private Long completedTrips;
    @Column(name = "TYPE", columnDefinition = "bigint default 0", nullable = false)
    private VehicleType type;
    //numeriai masinos
    private String lastService;
    @ManyToOne
    @JoinColumn(name = "assignedID")
    private User assignedId;
}

