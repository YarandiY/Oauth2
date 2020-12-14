package ir.tourismit.sampleLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserSummary {
    private Long id;
    private String username;
    private String email;
    private String mobile;
    private String name;
}