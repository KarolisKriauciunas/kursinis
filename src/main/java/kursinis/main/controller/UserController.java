package kursinis.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.User.CreateUserRequest;
import kursinis.main.model.api.Login.LoginResponse;
import kursinis.main.model.api.User.UserResponse;
import kursinis.main.model.domain.Account.AccountType;
import kursinis.main.model.domain.Account.User;
import kursinis.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    @Operation(summary = "Create User in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New USER successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createUser(@Validated @RequestBody CreateUserRequest request) {
        return userService.createUser(request).getUserId();
    }

    @GetMapping(value = "/users")
    @Operation(summary = "Get specific User by username or all if no Username provided")
    public List<UserResponse> fetchUsers(@RequestParam(required = false) String userName) {
        return userService.fetchUsers(userName).stream()
                .map(p -> new UserResponse(p.getFirstName(), p.getLastName(), p.getPassword(), p.getUserName(),p.getUserId(), p.getType(), p.getEmail()))
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/users/{userId}")
    @Operation(summary = "Delete User from database")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/user/{id}")
    @Operation(summary = "Update a user info in database")
    ResponseEntity<Void> replaceUser(@PathVariable Long id, @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String lastName, @RequestParam(required = false) String password,
                                     @RequestParam(required = false) String userName, @RequestParam(required = false) Float salary, @RequestParam(required = false)AccountType type, @RequestParam(required = false) String email) {
        userService.updateUser(id, name, lastName, password, userName, salary,type,email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset")
    @Operation(summary = "Update a user info in database")
    public String resetPassword(@RequestParam String email) {
        return userService.resetPassword(email);
    }

    @GetMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestParam String userName, @RequestParam String password) {
        Optional<User> user = userService.validateUser(userName, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(user.get().getUserName(),user.get().getType(),user.get().getUserId()));
            }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }

}
