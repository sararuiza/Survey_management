package Riwi.Survey_management.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestionResponse {

    private Long id;
    private String text;
    private Boolean active;

    private QuestionBasicResponse question;

    
}
