package kursinis.main.model.api.User;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.Account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class CreateUserRequest {
    @NonNull
    @Schema(description = "User First name", example = "Karolis")
    private String firstName;
    @NonNull
    @Schema(description = "User Last name", example = "KKKK")
    private String lastName;
    @NonNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{2,}$")
    @Schema(description = "User Password", example = "Test1")
    private String password;
    @NotNull
    @Schema(description = "Accounts username", example = "Admin")
    private String userName;
    @Schema(description = "email", example = "asnoriudirbti@dirbu.lt")
    private String email;

    @Schema(description = "account type", example = "0")
    private AccountType type;
}