package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dao.repository.QuestionGroupRepository;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTO;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.entity.QuestionGroup;
import by.ghoncharko.selectioninterview.error.CannotCreateQuestionGroupError;
import by.ghoncharko.selectioninterview.error.CannotDeleteQuestionGroupError;
import by.ghoncharko.selectioninterview.error.CannotUpdateQuestionGroupError;
import by.ghoncharko.selectioninterview.mapper.QuestionGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaHibernateQuestionGroupService implements QuestionGroupService{
    private static final QuestionGroupMapper questionGroupMapper = QuestionGroupMapper.INSTANCE;
    private final QuestionGroupRepository questionGroupRepository;

    @Override
    public List<QuestionGroupDTO> findByDeletedFalse(Pageable pageable) {
        Page<QuestionGroup> questionGroupPage = questionGroupRepository.findByDeletedFalse(pageable);
        return questionGroupMapper.mapQuestionGroupListToQuestionGroupDtoList(questionGroupPage.getContent());
    }

    @Override
    public List<QuestionGroupDTO> findByDeletedTrue(Pageable pageable) {
        Page<QuestionGroup> questionGroupPage = questionGroupRepository.findByDeletedTrue(pageable);
        return questionGroupMapper.mapQuestionGroupListToQuestionGroupDtoList(questionGroupPage.getContent());
    }

    @Override
    public List<QuestionGroupDTO> findAll(Pageable pageable) {
        Page<QuestionGroup> questionGroupPage = questionGroupRepository.findAll(pageable);
        return questionGroupMapper.mapQuestionGroupListToQuestionGroupDtoList(questionGroupPage.getContent());
    }

    @Override
    public Optional<QuestionGroupDTO> findById(BigInteger id) {
       Optional<QuestionGroup> questionGroup = questionGroupRepository.findById(id);
        return questionGroup.map(questionGroupMapper::questionGroupToQuestionGroupDto);
    }

    @Override
    public Optional<QuestionGroupDTO> findByGroupName(String name) {
       Optional<QuestionGroup> questionGroup = questionGroupRepository.findByGroupName(name);
       return questionGroup.map(questionGroupMapper::questionGroupToQuestionGroupDto);
    }

    @Override
    public QuestionGroupDTO create(QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate) {
        Optional<QuestionGroup> questionGroup = questionGroupRepository.findByGroupName(questionGroupDTOForCreateOrUpdate.getGroupName());
        if(questionGroup.isPresent()){
            throw new CannotCreateQuestionGroupError("Cannot create question group with name " + questionGroupDTOForCreateOrUpdate.getGroupName() + " because this name is present in database");
        }
        QuestionGroup questionGroupAfterMapping = questionGroupMapper.questionGroupDtoForCreateOrUpdateToQuestionGroup(questionGroupDTOForCreateOrUpdate);
        questionGroupAfterMapping.setDateCreated(new Timestamp(new Date().getTime()));
        questionGroupAfterMapping.setLastDateUpdated(new Timestamp(new Date().getTime()));
        QuestionGroup questionGroupAfterSave = questionGroupRepository.save(questionGroupAfterMapping);
        return questionGroupMapper.questionGroupToQuestionGroupDto(questionGroupAfterSave);
    }

    @Override
    public QuestionGroupDTO update(QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate) {
        Optional<QuestionGroup> questionGroupFindById = questionGroupRepository.findById(questionGroupDTOForCreateOrUpdate.getId());
        if(questionGroupFindById.isEmpty()){
            throw new CannotUpdateQuestionGroupError("Cannot update question group with id " + questionGroupDTOForCreateOrUpdate.getId() + " because this is not present in database");
        }
        QuestionGroup questionGroupById = questionGroupFindById.get();
        Optional<QuestionGroup> questionGroup = questionGroupRepository.findByGroupName(questionGroupDTOForCreateOrUpdate.getGroupName());
        if(questionGroup.isPresent() && !questionGroup.get().getId().equals(questionGroupDTOForCreateOrUpdate.getId())){
            throw new CannotUpdateQuestionGroupError("Cannot update question group with name " + questionGroupDTOForCreateOrUpdate.getGroupName() + " because this name is present in database");
        }
        QuestionGroup questionGroupForSave = questionGroupMapper.questionGroupDtoForCreateOrUpdateToQuestionGroup(questionGroupDTOForCreateOrUpdate);
        questionGroupForSave.setDateCreated(questionGroupById.getDateCreated());
        questionGroupForSave.setLastDateUpdated(new Timestamp(new Date().getTime()));
        QuestionGroup questionGroupAfterSave = questionGroupRepository.save(questionGroupForSave);
        return questionGroupMapper.questionGroupToQuestionGroupDto(questionGroupAfterSave);
    }

    @Override
    public void deleteById(BigInteger id) {
        Optional<QuestionGroup> questionGroupOptional = questionGroupRepository.findById(id);
        if(questionGroupOptional.isEmpty()){
            throw new CannotDeleteQuestionGroupError("Cannot delete question group by id with id " + id + " because question group is not present in database");
        }
        QuestionGroup questionGroup = questionGroupOptional.get();
        if(questionGroup.isDeleted()){
            throw new CannotDeleteQuestionGroupError("Cannot delete question group by id with id " + id + " because question group already deleted");
        }
        questionGroupRepository.deleteById(id);
    }

    @Override
    public void deleteByQuestionGroupName(String name) {
        Optional<QuestionGroup> questionGroupOptional = questionGroupRepository.findByGroupName(name);
        if(questionGroupOptional.isEmpty()){
            throw new CannotDeleteQuestionGroupError("Cannot delete question group by name with name " + name + " because question group is not present in database");
        }
        QuestionGroup questionGroup = questionGroupOptional.get();
        if(questionGroup.isDeleted()){
            throw new CannotDeleteQuestionGroupError("Cannot delete question group by name with name " + name + " because question group already deleted");
        }
        questionGroupRepository.deleteByQuestionGroupName(name);
    }
}
