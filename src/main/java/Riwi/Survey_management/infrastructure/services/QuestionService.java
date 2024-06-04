package Riwi.Survey_management.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Survey_management.api.dto.request.OptionQuestionRequest;
import Riwi.Survey_management.api.dto.request.QuestionListRequest;
import Riwi.Survey_management.api.dto.request.QuestionRequest;
import Riwi.Survey_management.api.dto.response.OptionQuestionBasicResponse;
import Riwi.Survey_management.api.dto.response.QuestionResponse;
import Riwi.Survey_management.api.dto.response.SurveyEntityBasicResponse;
import Riwi.Survey_management.api.dto.response.UserBasicResponse;
import Riwi.Survey_management.domain.entities.OptionQuestion;
import Riwi.Survey_management.domain.entities.Question;
import Riwi.Survey_management.domain.entities.SurveyEntity;
import Riwi.Survey_management.domain.entities.User;
import Riwi.Survey_management.domain.repositories.OptionQuestionRepository;
import Riwi.Survey_management.domain.repositories.QuestionRepository;
import Riwi.Survey_management.domain.repositories.SurveyRepository;
import Riwi.Survey_management.infrastructure.abstrac_service.IQuestionService;
import Riwi.Survey_management.utils.BadRequestException;
import Riwi.Survey_management.utils.enums.SortType;
import Riwi.Survey_management.utils.messages.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {
    
    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final OptionQuestionRepository optionRepository;

    @Autowired
    private final SurveyRepository surveyRepository;
    
    @Override
    public QuestionResponse create(QuestionListRequest request) {

        SurveyEntity survey = this.surveyRepository.findById(request.getSurveyId())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("survey")));

        Question question = this.requestToEntity(request);
        question.setSurvey(survey);

        return entityToResponse(this.questionRepository.save(question));

        
    }

    // @Override
    // public QuestionResponse getById(Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getById'");
    // }

    // @Override
    // public QuestionResponse update(QuestionRequest request, Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'update'");
    // }

    @Override
    public void delete(Long id) {
        Question question = this.find(id);
        this.questionRepository.delete(question);
    }

    // @Override
    // public Page<QuestionResponse > getAll(int page, int size, SortType sortType) {
    //     if (page < 0)
    //         page = 0;

    //     PageRequest pagination = null;

    //     switch (sortType) {
    //         case NONE -> pagination = PageRequest.of(page, size);
    //         case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
    //         case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
    //     }

    //     pagination = PageRequest.of(page, size);

    //     return this.surveyRepository.findAll(pagination)
    //             .map(this::entityToResponse);
    // }


    private QuestionResponse entityToResponse(Question entity){


        SurveyEntityBasicResponse survey = new SurveyEntityBasicResponse();
        if (entity.getSurvey() != null) {
            BeanUtils.copyProperties(entity.getSurvey(), survey);
        }

        List<OptionQuestionBasicResponse> optionQuestions= entity.getOptionQuestions()
        .stream()
        .map(temp -> OptionQuestionBasicResponse.builder()
        .id(temp.getId())
        .text(temp.getText())
        .active(temp.getActive())
        .build())
        .collect(Collectors.toList());

        return QuestionResponse.builder()
        .id(entity.getId())
        .text(entity.getText())
        .tipe(entity.getTipe())
        .active(entity.getActive())
        .survey(survey)
        .optionQuestions(optionQuestions)
        .build();

    }

    private Question requestToEntity(QuestionListRequest request){

        List<OptionQuestion> options = request.getOptions()
            .stream()
            .map(this::requestToEntityOption)
            .collect(Collectors.toList());


        return Question.builder()
        
        .text(request.getText())
        .tipe(request.getTipe())
        .active(request.getActive())
        .optionQuestions(options)
        .build();
    }

    private OptionQuestion requestToEntityOption(OptionQuestionRequest request){

        var opt = OptionQuestion.builder()
        .text(request.getText())
        .active(request.getActive())
        .build();

        return this.optionRepository.save(opt);
    }
    

    private Question find(Long id) {
        return this.questionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("question")));
    }








    
}
