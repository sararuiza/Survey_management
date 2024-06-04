package Riwi.Survey_management.infrastructure.abstrac_service;

import org.springframework.data.domain.Page;

import Riwi.Survey_management.api.dto.request.QuestionListRequest;
import Riwi.Survey_management.api.dto.request.QuestionRequest;
import Riwi.Survey_management.api.dto.response.QuestionResponse;
import Riwi.Survey_management.utils.enums.SortType;

public interface IQuestionService {
    
    public QuestionResponse create(QuestionListRequest request);

    // public QuestionResponse getById(ID id);

    // public QuestionResponse update(RQ request, ID id);

    // public void delete(Long id);

    // public Page<RS> getAll(int page, int size, SortType sortType);
    
    
    public final String FIELD_BY_SORT = "tipe";



}
