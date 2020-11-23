package ir.tourismit.sampleLogin.service;

import ir.tourismit.sampleLogin.dto.UserDTO;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String login(String username, String password);
    long signUp(UserDTO userDTO) throws DuplicateMemberException;
    String sayHello(int userId);
}
