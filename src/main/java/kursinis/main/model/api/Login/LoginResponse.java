package kursinis.main.model.api.Login;

import io.swagger.v3.oas.annotations.media.Schema;
import kursinis.main.model.domain.Account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    @Schema(description = "Users account name", example = "admin")
    private String userName;
    @Schema(description = "type", example = "DRIVER")
    private AccountType type;
    @Schema(description = "id", example = "15")
    private Long employeeID;
}
