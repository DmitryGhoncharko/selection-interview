package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
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
    QuestionType questionTypeDtoToQuestionType(QuestionTypeDTO questionTypeDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    @Mapping(target = "questions", source = "questions")
    QuestionTypeDTO questionTypeToQuestionTypeDto(QuestionType questionType);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    QuestionType questionTypeDtoWithoutQuestionsToQuestionType(QuestionTypeDTOWithoutQuestions questionTypeDTOWithoutQuestions);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionTypeName", source = "questionTypeName")
    QuestionTypeDTOWithoutQuestions questionTypeToQuestionTypeDtoWithoutQuestions(QuestionType questionType);
    default List<QuestionType> mapQuestionTypeDtoListToQuestionTypeList(List<QuestionTypeDTO> questionTypeDTOS){
        return questionTypeDTOS.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionTypeDtoToQuestionType)
                .collect(Collectors.toList());
    }

    default List<QuestionTypeDTO> mapQuestionTypeListToQuestionTypeDtoList(List<QuestionType> questionTypes){
        return questionTypes.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionTypeToQuestionTypeDto)
                .collect(Collectors.toList());
    }

    default List<QuestionTypeDTOWithoutQuestions> mapQuestionTypeListToQuestionTypeDtoWithoutQuestionsList(List<QuestionType> questionTypes){
        return questionTypes.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionTypeToQuestionTypeDtoWithoutQuestions)
                .collect(Collectors.toList());
    }
}
