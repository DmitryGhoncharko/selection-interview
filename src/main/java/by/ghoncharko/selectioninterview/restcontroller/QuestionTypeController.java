package by.ghoncharko.selectioninterview.restcontroller;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOForCreateOrUpdate;
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

    @GetMapping("/all/without/questions/{page}")
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

    @GetMapping("/find/without/questions/by/id/{id}")
    public ResponseEntity<QuestionTypeDTOWithoutQuestions> findById(@PathVariable BigInteger id) {
        return questionTypeService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/by/name/{name}")
    public ResponseEntity<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(@PathVariable String name) {
        return questionTypeService.findByQuestionTypeNameAllQuestionsWithoutLazy(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/without/questions/by/name/ignore/case/{name}")
    public ResponseEntity<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(@PathVariable String name) {
        return questionTypeService.findByQuestionTypeNameIgnoreCase(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<QuestionTypeDTO> createQuestionType(@RequestBody @Validated QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionTypeService.create(questionTypeDtoForCreateOrUpdate));
    }

    @PutMapping
    public ResponseEntity<QuestionTypeDTO> updateQuestionType(@RequestBody @Validated(UpdateValidationGroup.class) QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate) {
        return ResponseEntity.ok(questionTypeService.update(questionTypeDtoForCreateOrUpdate));
    }
    @GetMapping("/find/without/questions/all/by/deleted/is/true/{page}")
    public ResponseEntity<List<QuestionTypeDTOWithoutQuestions>> findAllByDeletedIsTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionTypeService.findAllByDeletedIsTrue(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/without/questions/all/by/deleted/is/false/{page}")
    public ResponseEntity<List<QuestionTypeDTOWithoutQuestions>> findAllByDeletedIsFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionTypeService.findAllByDeletedIsFalse(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/all/by/deleted/is/false/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazyDeletedQuestionTypeFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazyDeletedQuestionTypeFalse(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/all/by/deleted/is/false/and/deleted/question/is/false/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionFalse(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/all/by/deleted/is/false/and/deleted/question/is/true/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionTrue(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/all/by/deleted/is/true/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazyDeletedQuestionTypeTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazyDeletedQuestionTypeTrue(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/all/by/deleted/is/true/and/deleted/question/is/false/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsFalse(PageRequest.of(page, pageSize)));
    }
    @GetMapping("/find/all/by/deleted/is/true/and/deleted/question/is/true/{page}")
    public ResponseEntity<List<QuestionTypeDTO>> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(questionTypeService.findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsTrue(PageRequest.of(page, pageSize)));
    }
}
