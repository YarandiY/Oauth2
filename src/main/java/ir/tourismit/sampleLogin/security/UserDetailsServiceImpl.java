package ir.tourismit.sampleLogin.security;

import ir.tourismit.sampleLogin.exception.NotFoundException;
import ir.tourismit.sampleLogin.model.User;
import ir.tourismit.sampleLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("User not found [email: " + username + "]")
                );
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found [id: " + id + "]")
        );
    }

}
