package ir.tourismit.sampleLogin.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String name;
}
