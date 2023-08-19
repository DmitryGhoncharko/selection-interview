package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.QuestionDTO;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTO;
import by.ghoncharko.selectioninterview.dto.QuestionGroupDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.entity.Question;
import by.ghoncharko.selectioninterview.entity.QuestionGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface QuestionGroupMapper {
    QuestionGroupMapper INSTANCE = Mappers.getMapper(QuestionGroupMapper.class);
    @Mapping(target = "id",source = "id")
    @Mapping(target = "groupName",source = "groupName")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    @Mapping(target = "deleted",source = "deleted")
    QuestionGroupDTO questionGroupToQuestionGroupDto(QuestionGroup questionGroup);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "groupName",source = "groupName")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    @Mapping(target = "deleted",source = "deleted")
    QuestionGroup questionGroupDtoToQuestionGroup(QuestionGroupDTO questionGroupDTO);
    @Mapping(target = "id",source = "id")
    @Mapping(target = "groupName",source = "groupName")
    @Mapping(target = "deleted",source = "deleted")
    QuestionGroup questionGroupDtoForCreateOrUpdateToQuestionGroup(QuestionGroupDTOForCreateOrUpdate questionGroupDTOForCreateOrUpdate);

    default List<QuestionGroupDTO> mapQuestionGroupListToQuestionGroupDtoList(List<QuestionGroup> questionGroupList){
        return questionGroupList.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionGroupToQuestionGroupDto)
                .collect(Collectors.toList());
    }
}
