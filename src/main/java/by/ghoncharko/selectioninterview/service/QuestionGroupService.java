package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dto.QuestionGroupDTO;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTOForCreateOrUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface QuestionGroupService {
    List<QuestionGroupDTO> findByDeletedFalse(Pageable pageable);

    List<QuestionGroupDTO> findByDeletedTrue(Pageable pageable);

    List<QuestionGroupDTO> findAll(Pageable pageable);


    Optional<QuestionGroupDTO> findById(BigInteger integer);

    Optional<QuestionGroupDTO> findByGroupName(String name);

    QuestionGroupDTO create(QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate);

    QuestionGroupDTO update(QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate);

    void deleteById(BigInteger id);

    void deleteByQuestionGroupName(String name);
}
