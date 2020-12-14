package ir.tourismit.sampleLogin.service;

import ir.tourismit.sampleLogin.dto.UserSummary;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserSummary getUserById(long id);
}
