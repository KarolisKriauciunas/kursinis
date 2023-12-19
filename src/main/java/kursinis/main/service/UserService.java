package kursinis.main.service;

import kursinis.main.model.api.User.CreateUserRequest;
import kursinis.main.model.domain.Account.AccountType;
import kursinis.main.model.domain.Account.User;
import kursinis.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchUsers(String username) {
        if (username != null) return userRepository.findAllByUserName(username);
        return userRepository.findAll();
    }

    public Optional<User> fetchUser(Long Id) {
        return userRepository.findById(Id);
    }

    public User createUser(CreateUserRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .userName(request.getUserName())
                .Type(request.getType())
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }

    public void updateUser(Long id, String name, String lastName, String password, String userName, Float salary, AccountType type, String email) {

        Optional<User> user = fetchUser(id);
        if (user.isPresent()) {
            if (name != null) user.get().setFirstName(name);
            if (lastName != null) user.get().setLastName(lastName);
            if (password != null) user.get().setPassword(password);
            if (userName != null) user.get().setUserName(userName);
            if (type != null) user.get().setType(type);
            if (email != null) user.get().setEmail(email);
            userRepository.save(user.get());
        }
    }

    public String resetPassword(String email) {
        String password = generateStrongPassword();
        List<User> user = userRepository.findByEmail(email);
        user.get(0).setPassword(password);
        userRepository.save(user.get(0));
        return password;
    }

    public Optional<User> validateUser(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    public static String generateStrongPassword() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[12];
        secureRandom.nextBytes(bytes);

        String generatedPassword = Base64.getEncoder().encodeToString(bytes);

        generatedPassword = generatedPassword.replaceAll("[^a-zA-Z0-9]", "");

        generatedPassword = generatedPassword.substring(0, Math.min(12, generatedPassword.length()));

        return generatedPassword;
    }
}
