package by.ghoncharko.selectioninterview.restcontroller;

import by.ghoncharko.selectioninterview.dto.AnswerDTO;
import by.ghoncharko.selectioninterview.dto.AnswerDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.AnswerDTOWithoutQuestion;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.service.AnswerService;
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
@RequestMapping("api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping("/all/without/question/{page}")
    public ResponseEntity<List<AnswerDTOWithoutQuestion>> findAll(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(answerService.findAll(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/question/deleted/true/{page}")
    public ResponseEntity<List<AnswerDTOWithoutQuestion>> findAllByDeletedIsTrue(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(answerService.findAllByDeletedIsTrue(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/all/without/question/deleted/false/{page}")
    public ResponseEntity<List<AnswerDTOWithoutQuestion>> findAllByDeletedIsFalse(@PathVariable int page, @RequestParam(defaultValue = "50", name = "pageSize") int pageSize){
        return ResponseEntity.ok(answerService.findAllByDeletedIsFalse(PageRequest.of(page,pageSize)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        answerService.deleteAnswerById(BigInteger.valueOf(id));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/body/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name){
        answerService.deleteAnswerByBody(name);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<AnswerDTOWithoutQuestion> create(@RequestBody @Validated AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(answerService.create(answerDTOForCreateOrUpdate));
    }
    @PutMapping
    public ResponseEntity<AnswerDTOWithoutQuestion> update(@RequestBody @Validated AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate){
        return ResponseEntity.status(HttpStatus.OK)
                .body(answerService.update(answerDTOForCreateOrUpdate));
    }
}
