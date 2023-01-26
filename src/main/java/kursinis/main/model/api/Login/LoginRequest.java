package kursinis.main.model.api.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
   private String username;
   private String password;
}