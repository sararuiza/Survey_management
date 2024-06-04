package Riwi.Survey_management.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestionRequest {
    private Long id;
    private String text;
    private Boolean active;
    
}
