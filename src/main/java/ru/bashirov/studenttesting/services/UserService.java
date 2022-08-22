package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bashirov.studenttesting.models.User;
import ru.bashirov.studenttesting.repositories.UsersRepository;
import ru.bashirov.studenttesting.sequrity.UserDetails;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    public long getUsersCount() {
        return usersRepository.count();
    }

    public long getStudentsCount() {
        return usersRepository.countByRole("ROLE_USER");
    }

    public long getTeachersCount() {
        return usersRepository.countByRole("ROLE_TEACHER");
    }


    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public List<User> getUsersByLoginStartingWith(String query) {
        return usersRepository.findByLoginStartingWith(query);
    }

    @Transactional
    public void deleteById(int id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    public void disableUserById(int id) {
        Optional<User> user = usersRepository.findById(id);
        user.ifPresent(findUser -> {
            findUser.setRole("ROLE_DISABLED");
            usersRepository.save(user.get());
        });
    }

    @Transactional
    public void update(User user) {
        Optional<User> findUser = usersRepository.findById(user.getId());

        findUser.ifPresent(value -> {
            value.setFirstName(user.getFirstName());
            value.setLastName(user.getLastName());

            getCurrentUser().setFirstName(user.getFirstName());
            getCurrentUser().setLastName(user.getLastName());
            usersRepository.save(value);
        });
    }
}
