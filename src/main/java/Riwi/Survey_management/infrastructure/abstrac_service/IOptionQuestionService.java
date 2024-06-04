package Riwi.Survey_management.infrastructure.abstrac_service;

import Riwi.Survey_management.api.dto.request.OptionQuestionRequest;
import Riwi.Survey_management.api.dto.response.OptionQuestionResponse;

public interface IOptionQuestionService extends CrudService<OptionQuestionRequest, OptionQuestionResponse,Long> {
    
}
