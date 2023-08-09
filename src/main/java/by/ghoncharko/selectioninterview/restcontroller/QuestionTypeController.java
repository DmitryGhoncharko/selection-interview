package by.ghoncharko.selectioninterview.restcontroller;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import by.ghoncharko.selectioninterview.service.QuestionTypeService;
import by.ghoncharko.selectioninterview.validation.group.UpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("api/v1/question_type")
@RequiredArgsConstructor
public class QuestionTypeController {

    private final QuestionTypeService questionTypeService;

    @GetMapping("/all/lazy/{page}")
    public ResponseEntity<List<QuestionTypeDTOWithoutQuestions>> findAll(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAll(PageRequest.of(page, pageSize)));
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazy(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazy(PageRequest.of(page, pageSize)));
    }

    @GetMapping("/find/by/id/{id}")
    public ResponseEntity<QuestionTypeDTO> findByQuestionTypeIdAllQuestionsWithoutLazy(@PathVariable BigInteger id) {
        return questionTypeService.findByQuestionTypeIdAllQuestionsWithoutLazy(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/lazy/by/id/{id}")
    public ResponseEntity<QuestionTypeDTOWithoutQuestions> findById(@PathVariable BigInteger id) {
        return questionTypeService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/by/name/{name}")
    public ResponseEntity<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(@PathVariable String name) {
        return questionTypeService.findByQuestionTypeNameAllQuestionsWithoutLazy(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/lazy/by/name/ignore/case/{name}")
    public ResponseEntity<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(@PathVariable String name) {
        return questionTypeService.findByQuestionTypeNameIgnoreCase(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable BigInteger id) {
        questionTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByQuestionTypeName(@PathVariable String name) {
        questionTypeService.deleteByQuestionTypeName(name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<QuestionTypeDTO> createQuestionType(@RequestBody @Validated QuestionTypeDTO questionTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionTypeService.create(questionTypeDTO));
    }

    @PutMapping
    public ResponseEntity<QuestionTypeDTO> updateQuestionType(@RequestBody @Validated(UpdateValidationGroup.class) QuestionTypeDTO questionTypeDTO) {
        return ResponseEntity.ok(questionTypeService.update(questionTypeDTO));
    }
}
