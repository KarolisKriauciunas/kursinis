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
@Table(name = "Trip")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @ManyToOne
    @JoinColumn(name = "Driver_ID", nullable = false)
    private User driver;
    private Timestamp tripStartDate;
    private Timestamp tripEndDate;
    private String destination;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tripID;
    @OneToOne()
    @JoinColumn(name = "Cargo_id", nullable = false)
    private Cargo merchandiseID;
    @OneToMany (mappedBy = "tripID",cascade = CascadeType.ALL)
    private List<TripStop> tripStopList = new ArrayList<>();


}
