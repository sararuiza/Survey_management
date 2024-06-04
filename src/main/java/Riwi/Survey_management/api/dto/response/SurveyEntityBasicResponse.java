package Riwi.Survey_management.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEntityBasicResponse {

    
    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Boolean active;

    


    
}
