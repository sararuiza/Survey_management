package Riwi.Survey_management.api.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyEntityRequest {
    
    private String title;
    private String description;
    private LocalDate creationDate;
    private Boolean active;

    private Long creatorid;

    
}
