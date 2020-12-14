package ir.tourismit.sampleLogin.service;

import ir.tourismit.sampleLogin.dto.UserSummary;
import ir.tourismit.sampleLogin.exception.NotFoundException;
import ir.tourismit.sampleLogin.model.User;
import ir.tourismit.sampleLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserSummary getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found with [id: " + id + "]"));
        return new UserSummary(user.getId(),user.getUsername(),user.getEmail(),user.getMobile(),user.getName());
    }
}
