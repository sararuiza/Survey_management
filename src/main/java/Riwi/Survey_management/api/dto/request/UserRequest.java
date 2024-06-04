package Riwi.Survey_management.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    @Size(min=2, max=100, message="name between 2 and 100 characters")
    private String name;
    @Email(message="the email is not correct")
    private String email;
    @Size(min=10, max=100, message ="password between 10 and 100 characters")
    private String password;
    private Boolean active;

        





    
}
