package Riwi.Survey_management.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Survey_management.api.dto.request.UserRequest;
import Riwi.Survey_management.api.dto.response.SurveyEntityBasicResponse;
import Riwi.Survey_management.api.dto.response.UserResponse;
import Riwi.Survey_management.domain.entities.User;
import Riwi.Survey_management.domain.repositories.UserRepository;
import Riwi.Survey_management.infrastructure.abstrac_service.IUserService;
import Riwi.Survey_management.utils.BadRequestException;
import Riwi.Survey_management.utils.enums.SortType;
import Riwi.Survey_management.utils.messages.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        pagination = PageRequest.of(page, size);
        
        return this.userRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    private UserResponse entityToResponse(User entity){

        List<SurveyEntityBasicResponse> surveys = entity.getSurvey()
        .stream()
        .map(temp -> SurveyEntityBasicResponse.builder()
        .id(temp.getId())
        .title(temp.getTitle())
        .description(temp.getDescription())
        .creationDate(temp.getCreationDate())
        .active(temp.getActive())
        .build())
        .collect(Collectors.toList());
        

        return UserResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .email(entity.getEmail())
        .active(entity.getActive())
        .survey(surveys)
        .build();
    }


    private User requestToEntity(UserRequest request){
        return User.builder()
        .id(request.getId())
        .name(request.getName())
        .email(request.getEmail())
        .password(request.getPassword())
        .active(request.getActive())
        .build();
    }

    private User find(Long id){
        return this.userRepository.findById(id)
        .orElseThrow(()->new BadRequestException(ErrorMessage.idNotFound("user")));
    }
    
}
