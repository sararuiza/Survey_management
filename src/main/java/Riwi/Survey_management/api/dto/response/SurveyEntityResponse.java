package Riwi.Survey_management.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEntityResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean active;

    private UserBasicResponse creator;
    private List<QuestionBasicResponse> question;


    
}
