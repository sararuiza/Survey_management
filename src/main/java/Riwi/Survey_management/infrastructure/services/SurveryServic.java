package Riwi.Survey_management.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Survey_management.api.dto.request.SurveyEntityRequest;
import Riwi.Survey_management.api.dto.response.QuestionBasicResponse;
import Riwi.Survey_management.api.dto.response.SurveyEntityResponse;
import Riwi.Survey_management.api.dto.response.UserBasicResponse;
import Riwi.Survey_management.domain.entities.SurveyEntity;
import Riwi.Survey_management.domain.entities.User;
import Riwi.Survey_management.domain.repositories.SurveyRepository;
import Riwi.Survey_management.domain.repositories.UserRepository;
import Riwi.Survey_management.infrastructure.abstrac_service.ISurveyService;
import Riwi.Survey_management.utils.BadRequestException;
import Riwi.Survey_management.utils.enums.SortType;
import Riwi.Survey_management.utils.messages.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveryServic implements ISurveyService {
    
    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final UserRepository userRepository;
    
    @Override
    public SurveyEntityResponse create(SurveyEntityRequest request) {

        User user = this.userRepository.findById(request.getCreatorid())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));

        SurveyEntity survey = this.requestToEntity(request);
        
        survey.setQuestion(new ArrayList<>());
        survey.setCreator(user);

        return entityToResponse(this.surveyRepository.save(survey));
    }

    @Override
    public SurveyEntityResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public SurveyEntityResponse update(SurveyEntityRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<SurveyEntityResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        pagination = PageRequest.of(page, size);

        return this.surveyRepository.findAll(pagination)
                .map(this::entityToResponse);
    }


    private SurveyEntityResponse entityToResponse(SurveyEntity entity){

         UserBasicResponse user = new UserBasicResponse();
        if (entity.getCreator() != null) {
            BeanUtils.copyProperties(entity.getCreator(), user);
        }

        List<QuestionBasicResponse> question = entity.getQuestion()
        .stream()
        .map(temp -> QuestionBasicResponse.builder()
        .id(temp.getId())
        .text(temp.getText())
        .tipe(temp.getTipe())
        .active(temp.getActive())
        .build())
        .collect(Collectors.toList());

        return SurveyEntityResponse.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .creationDate(entity.getCreationDate())
        .active(entity.getActive())
        .creator(user)
        .question(question)
        .build();

    }
    

    private SurveyEntity requestToEntity(SurveyEntityRequest request){
        
        return SurveyEntity.builder()
        
        .title(request.getTitle())
        .description(request.getDescription())
        .creationDate(request.getCreationDate())
        .active(request.getActive())
        
        .build();
    }


    private SurveyEntity find(Long id) {
        return this.surveyRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("survey")));
    }



}
