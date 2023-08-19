package by.ghoncharko.selectioninterview.restcontroller;

import by.ghoncharko.selectioninterview.dto.AnswerDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTO;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.service.QuestionGroupService;
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
@RequiredArgsConstructor
@RequestMapping("api/v1/question-group")
public class QuestionGroupController {
    private final QuestionGroupService questionGroupService;

    @GetMapping("/all/deleted/false/{page}")
    public ResponseEntity<List<QuestionGroupDTO>> findByDeletedFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionGroupService.findByDeletedFalse(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/deleted/true/{page}")
    public ResponseEntity<List<QuestionGroupDTO>> findByDeletedTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionGroupService.findByDeletedTrue(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/{page}")
    public ResponseEntity<List<QuestionGroupDTO>> findAll(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(questionGroupService.findAll(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/find/by/id/{id}")
    public ResponseEntity<QuestionGroupDTO> findById(@PathVariable BigInteger id){
        Optional<QuestionGroupDTO> questionGroupDTOOptional = questionGroupService.findById(id);
        return questionGroupDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/find/by/group/name/{name}")
    public ResponseEntity<QuestionGroupDTO> findById(@PathVariable String name){
        Optional<QuestionGroupDTO> questionGroupDTOOptional = questionGroupService.findByGroupName(name);
        return questionGroupDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<QuestionGroupDTO> create(@RequestBody @Validated QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionGroupService.create(questionGroupDTOForCreateOrUpdate));
    }
    @PutMapping
    public ResponseEntity<QuestionGroupDTO> update(@RequestBody @Validated(UpdateValidationGroup.class) QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate){
        return ResponseEntity.status(HttpStatus.OK).body(questionGroupService.update(questionGroupDTOForCreateOrUpdate));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable BigInteger id){
        questionGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name){
        questionGroupService.deleteByQuestionGroupName(name);
        return ResponseEntity.noContent().build();
    }
}
