package peaksoft.service.serviceImmpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.User;
import peaksoft.repo.UserRepo;
import peaksoft.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new NullPointerException(
                        "User with Id: " + id + "is not found"
                )
        );
    }

    @Override
    public void updateUser(Long id, User user) {
        User user1 = getUserById(id);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        userRepo.save(user1);


    }

    @Override
    public void deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else throw new NullPointerException(
                "User with id: " + id + "doesn't exists"
        );
    }
}
