package Riwi.Survey_management.infrastructure.abstrac_service;

import Riwi.Survey_management.api.dto.request.QuestionRequest;
import Riwi.Survey_management.api.dto.response.QuestionResponse;

public interface IQuestionService extends CrudService<QuestionRequest,QuestionResponse,Long> {
    public final String FIELD_BY_SORT = "tipe";
}
