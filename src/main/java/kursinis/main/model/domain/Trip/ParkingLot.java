package kursinis.main.model.domain.Trip;

import kursinis.main.model.domain.Account.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity()
@Table(name = "ParkingLot")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
    private String address;
    private City city;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long parkingLotId;

    @OneToMany(mappedBy = "parkingSpaceId",cascade = CascadeType.ALL)
    private List<ParkingSpace> parkingSpaceList = new ArrayList<>();
}