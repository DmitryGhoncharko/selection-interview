package by.ghoncharko.selectioninterview.restcontroller;

import by.ghoncharko.selectioninterview.dto.QuestionDTO;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswer;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswersAndType;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutQuestionType;
import by.ghoncharko.selectioninterview.dto.QuestionDtoForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.service.QuestionService;
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
import java.util.Optional;

@RestController
@RequestMapping("api/v1/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/find/without/answers/and/type/by/question/body/ignore/case/{body}")
    public ResponseEntity<QuestionDTOWithoutAnswersAndType> findByQuestionBodyIgnoreCase(@PathVariable String body){
        Optional<QuestionDTOWithoutAnswersAndType> questionDTOWithoutAnswersAndTypeOptional = questionService.findByQuestionBodyIgnoreCase(body);
        return questionDTOWithoutAnswersAndTypeOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/all/without/answers/and/type/{page}")
    public ResponseEntity<List<QuestionDTOWithoutAnswersAndType>> findAll(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAll(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/{page}")
    public ResponseEntity<List<QuestionDTO>> findAllWithoutLazyWithAnswersAndQuestionTypes(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithAnswersAndQuestionTypes(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/type/{page}")
    public ResponseEntity<List<QuestionDTOWithoutQuestionType>> findAllWithoutLazyWithAnswers(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithAnswers(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/answers/{page}")
    public ResponseEntity<List<QuestionDTOWithoutAnswer>> findAllWithoutLazyWithQuestionTypes(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithQuestionTypes(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/find/without/type/by/body/{body}")
    public ResponseEntity<QuestionDTOWithoutQuestionType> findByQuestionBodyWithoutLazyAnswers(@PathVariable String body){
        Optional<QuestionDTOWithoutQuestionType> questionDTOWithoutQuestionType = questionService.findByQuestionBodyWithoutLazyAnswers(body);
        return questionDTOWithoutQuestionType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/all/without/answers/and/type/deleted/false/{page}")
    public ResponseEntity<List<QuestionDTOWithoutAnswersAndType>> findAllByDeletedIsFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllByDeletedIsFalse(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/answers/and/type/deleted/true/{page}")
    public ResponseEntity<List<QuestionDTOWithoutAnswersAndType>> findAllByDeletedIsTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllByDeletedIsTrue(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/type/answer/deleted/false/{page}")
    public ResponseEntity<List<QuestionDTOWithoutQuestionType>> findAllWithoutLazyWithAnswersWhereAnswersDeletedFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithAnswersWhereAnswersDeletedFalse(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/type/answer/deleted/true/{page}")
    public ResponseEntity<List<QuestionDTOWithoutQuestionType>> findAllWithoutLazyWithAnswersWhereAnswersDeletedTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithAnswersWhereAnswersDeletedTrue(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/answer/type/deleted/false/{page}")
    public ResponseEntity<List<QuestionDTOWithoutAnswer>> findAllWithoutLazyWithQuestionTypesDeletedFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithQuestionTypesDeletedFalse(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/answer/type/deleted/true/{page}")
    public ResponseEntity<List<QuestionDTOWithoutAnswer>> findAllWithoutLazyWithQuestionTypesDeletedTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionService.findAllWithoutLazyWithQuestionTypesDeletedTrue(PageRequest.of(page,pageSize)));
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        questionService.deleteById(BigInteger.valueOf(id));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/body/{body}")
    public ResponseEntity<Void> deleteByName(@PathVariable String body){
        questionService.deleteByQuestionBody(body);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<QuestionDTO> create(@RequestBody @Validated QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(questionService.create(questionDtoForCreateOrUpdate));
    }
    @PutMapping
    public ResponseEntity<QuestionDTO> update(@RequestBody @Validated(UpdateValidationGroup.class) QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate){
        return ResponseEntity.ok(questionService.update(questionDtoForCreateOrUpdate));
    }
}

