package kursinis.main.model.domain.Trip;

import kursinis.main.model.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "ParkingSpace")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpace {

    @OneToMany (mappedBy = "parkingSpaceId",cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parkingLotId")
    private ParkingLot parkingLotID;

    private float price;
}
