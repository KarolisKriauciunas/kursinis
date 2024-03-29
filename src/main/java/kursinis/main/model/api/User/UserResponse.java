package kursinis.main.model.api.User;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.Account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@AllArgsConstructor
public class UserResponse {
    @Schema(description = "Person first name", example = "Karolis")
    private String firstName;
    @Schema(description = "Person last name", example = "L")
    private String lastName;
    @Schema(description = "User password", example = "Encrypted password")
    private String password;
    @Schema(description = "Users account name", example = "admin")
    private String userName;
    private Long employeeID;
    private AccountType type;
    private String email;
}
