package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import by.ghoncharko.selectioninterview.entity.QuestionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface QuestionTypeMapper {
    QuestionTypeMapper INSTANCE = Mappers.getMapper(QuestionTypeMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "questions", source = "questions")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "deleted", source = "deleted")
    QuestionType questionTypeDtoToQuestionType(QuestionTypeDTO questionTypeDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "questions", source = "questions")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "deleted", source = "deleted")
    QuestionTypeDTO questionTypeToQuestionTypeDto(QuestionType questionType);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "deleted", source = "deleted")
    QuestionType questionTypeDtoWithoutQuestionsToQuestionType(QuestionTypeDTOWithoutQuestions questionTypeDTOWithoutQuestions);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "deleted", source = "deleted")
    QuestionTypeDTOWithoutQuestions questionTypeToQuestionTypeDtoWithoutQuestions(QuestionType questionType);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "deleted", source = "deleted")
    QuestionType questionTypeToQuestionTypeDToForCrateOrUpdate(QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "deleted", source = "deleted")
    QuestionTypeDTOForCreateOrUpdate questionTypeToQuestionTypeDtoForCreateOrUpdate(QuestionType questionType);

    default List<QuestionType> mapQuestionTypeDtoListToQuestionTypeList(List<QuestionTypeDTO> questionTypeDTOS) {
        return questionTypeDTOS.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionTypeDtoToQuestionType)
                .collect(Collectors.toList());
    }

    default List<QuestionTypeDTO> mapQuestionTypeListToQuestionTypeDtoList(List<QuestionType> questionTypes) {
        return questionTypes.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionTypeToQuestionTypeDto)
                .collect(Collectors.toList());
    }

    default List<QuestionTypeDTOWithoutQuestions> mapQuestionTypeListToQuestionTypeDtoWithoutQuestionsList(List<QuestionType> questionTypes) {
        return questionTypes.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionTypeToQuestionTypeDtoWithoutQuestions)
                .collect(Collectors.toList());
    }
}
