package kursinis.main.model.domain.Trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "Trip_Stops")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripStop {
    @ManyToOne
    @JoinColumn(name = "trip_ID")
    private Trip tripID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stopID;
    private String description;
    private String stopAddress;

}
