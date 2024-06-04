package Riwi.Survey_management.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResponse {

    private Long id;
    private String name;
    private String email;
    private Boolean active;
    
}
