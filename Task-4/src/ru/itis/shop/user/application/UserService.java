package ru.itis.shop.user.application;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    public void signUp(String email, String password, String profileDescription) {
//        User user = new User(email, password, profileDescription);
//        userRepository.save(user);
//    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public void foundEmailById(String id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            System.out.println("--- Пользователь найден ---");
            System.out.println("Email: " + foundUser.get().getEmail());
        } else {
            System.out.println("Пользователь с таким id не найден.");
        }
    }

    public boolean checkEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return true;
        }else {
            return false;
        }
    }

    public void updateDescriptionByEmail(String email, String description) {
        userRepository.updateDescription(email,description);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
