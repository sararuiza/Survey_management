package Riwi.Survey_management.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Riwi.Survey_management.api.dto.request.QuestionListRequest;

import Riwi.Survey_management.api.dto.response.QuestionResponse;

import Riwi.Survey_management.infrastructure.services.QuestionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/questions")
@AllArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;


    @PostMapping
    public ResponseEntity<QuestionResponse> insert(
            @Validated
            @RequestBody QuestionListRequest request) {
        return ResponseEntity.ok(this.questionService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.questionService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
