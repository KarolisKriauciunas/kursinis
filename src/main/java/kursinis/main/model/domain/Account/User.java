package kursinis.main.model.domain.Account;

import kursinis.main.model.domain.Reservation;
import kursinis.main.model.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity()
@Table(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    private String Email;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private Long userId;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "assignedId", cascade = CascadeType.ALL)
    private List<Vehicle> vehicle;

    @Column(name = "ACCOUNT_TYPE", columnDefinition = "bigint default 0", nullable = false)
    private AccountType Type;
}