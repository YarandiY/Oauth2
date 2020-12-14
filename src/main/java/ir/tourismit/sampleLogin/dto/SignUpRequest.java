package ir.tourismit.sampleLogin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String name;
}
