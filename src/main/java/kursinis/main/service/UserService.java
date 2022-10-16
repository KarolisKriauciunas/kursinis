package kursinis.main.service;

import kursinis.main.model.api.CreateUserRequest;
import kursinis.main.model.domain.Account.User;
import kursinis.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(username != null)return userRepository.findAllByUserName(username);
        return userRepository.findAll();
    }
    public Optional<User> fetchUser(Long Id)
    {
        return userRepository.findUserByEmployeeID(Id);
    }
    public User createUser(CreateUserRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .salary(request.getSalary())
                .userName(request.getUserName())
                .Type(request.getType())
                .build();

        return userRepository.save(user);
    }
    public void deleteUser(Long userID){
        userRepository.deleteById(userID);
    }
    public void updateUser(Long id, String name, String lastName, String password, String userName, Float salary) {

        Optional<User> user = fetchUser(id);
        if(user.isPresent()) {
            if (name != null) user.get().setFirstName(name);
            if (lastName != null) user.get().setLastName(lastName);
            if (password != null) user.get().setPassword(password);
            if (userName != null) user.get().setUserName(userName);
            if (salary != null)user.get().setSalary(salary);
            userRepository.save(user.get());
        }
    }
}
