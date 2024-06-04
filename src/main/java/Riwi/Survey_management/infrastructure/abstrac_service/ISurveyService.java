package Riwi.Survey_management.infrastructure.abstrac_service;

import Riwi.Survey_management.api.dto.request.SurveyEntityRequest;
import Riwi.Survey_management.api.dto.response.SurveyEntityResponse;

public interface ISurveyService extends CrudService<SurveyEntityRequest,SurveyEntityResponse,Long> {
    public final String FIELD_BY_SORT = "creationDate";
}
