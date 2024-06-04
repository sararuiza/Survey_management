package Riwi.Survey_management.infrastructure.abstrac_service;

import Riwi.Survey_management.api.dto.request.UserRequest;
import Riwi.Survey_management.api.dto.response.UserResponse;

public interface IUserService extends CrudService<UserRequest,UserResponse,Long> {
    public final String FIELD_BY_SORT = "name";
    
}
