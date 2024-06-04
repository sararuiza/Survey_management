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
public class QuestionResponse {

    private Long id;
    private String text;
    private String tipe;
    private Boolean active;

    private SurveyEntityBasicResponse survey;
    private List<OptionQuestionBasicResponse> optionQuestions;



    
}
