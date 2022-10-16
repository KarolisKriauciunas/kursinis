package kursinis.main.model.domain.Trip;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Trip_Stops")
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
