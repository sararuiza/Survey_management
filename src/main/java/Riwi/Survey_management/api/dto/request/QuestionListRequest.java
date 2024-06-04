package Riwi.Survey_management.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListRequest {
    private Long surveyId;
    private String text;
    private String tipe;
    private Boolean active;

    private List<OptionQuestionRequest> options;


    
}
