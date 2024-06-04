package Riwi.Survey_management.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Riwi.Survey_management.api.dto.request.SurveyEntityRequest;
import Riwi.Survey_management.api.dto.request.UserRequest;
import Riwi.Survey_management.api.dto.response.SurveyEntityResponse;
import Riwi.Survey_management.api.dto.response.UserResponse;
import Riwi.Survey_management.domain.entities.SurveyEntity;
import Riwi.Survey_management.infrastructure.abstrac_service.ISurveyService;
import Riwi.Survey_management.utils.enums.SortType;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/surveys")
@AllArgsConstructor
public class SurveyController {

    @Autowired
    private final ISurveyService surveyService;

    @GetMapping
    public ResponseEntity<Page<SurveyEntityResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.surveyService.getAll(page - 1, size, sortType));

    }

    @PostMapping
    public ResponseEntity<SurveyEntityResponse> insert(
            @Validated
            @RequestBody SurveyEntityRequest request) {
        return ResponseEntity.ok(this.surveyService.create(request));
    }

    
}
