package ir.tourismit.sampleLogin.service;

import ir.tourismit.sampleLogin.dto.UserDTO;
import ir.tourismit.sampleLogin.model.User;
import ir.tourismit.sampleLogin.repository.UserRepository;
import javassist.bytecode.DuplicateMemberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl detailsService;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            logger.error(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new NullPointerException("//TODO -> login of userService");
        }
        final UserDetails userDetails = detailsService.loadUserByUsername(String.valueOf(username));
//        final String token = tokenUtil.generateToken(userDetails);
        return null;
    }

    @Override
    public long signUp(UserDTO userDTO) throws DuplicateMemberException {
        Optional<User> userOptional = userRepository.findByUsername(userDTO.getUsername());
        if(userOptional.isPresent())
            throw new DuplicateMemberException("duplicated user //TODO");
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        User savedUser =  userRepository.save(user);
        logger.info(new StringBuilder("A new User with username '"+savedUser.getUsername()+"' and Id '"+savedUser.getId()+"' was registered!"));
        return savedUser.getId();
    }

    @Override
    public String sayHello(int userId) {
        return null;
    }
}
