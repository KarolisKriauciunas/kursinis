package kursinis.main.model.domain.Account;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import kursinis.main.model.domain.Trip.Trip;
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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeID;
    @Column(name = "SALARY")
    private Float salary;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Trip> trips = new ArrayList<>();
    @OneToOne(mappedBy = "assignedId", cascade = CascadeType.ALL)
    private Vehicle vehicle;
    @Column(name = "ACCOUNT_TYPE", columnDefinition = "bigint default 0")
    private AccountType Type;

}