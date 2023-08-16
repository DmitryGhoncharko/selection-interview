package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.QuestionDTO;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswer;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswersAndType;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutQuestionType;
import by.ghoncharko.selectioninterview.dto.QuestionDtoForCreateOrUpdate;
import by.ghoncharko.selectioninterview.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "questionType", source = "questionTypeDTO")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "dateCreated",source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    Question questionDtoToQuestion(QuestionDTO questionDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "questionTypeDTO", source = "questionType")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "dateCreated",source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    QuestionDTO questionToQuestionDto(Question question);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "dateCreated",source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    QuestionDTOWithoutAnswersAndType questionToQuestionDtoWithoutAnswersAndType(Question question);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "dateCreated",source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    Question questionDtoWithoutAnswersAndTypeToQuestion(QuestionDTOWithoutAnswersAndType questionDTOWithoutAnswersAndType);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "questionTypeDTO", source = "questionType")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "dateCreated",source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    QuestionDTOWithoutAnswer questionToQuestionDtoWithoutAnswer(Question question);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "dateCreated",source = "dateCreated")
    @Mapping(target = "lastDateUpdated",source = "lastDateUpdated")
    QuestionDTOWithoutQuestionType questionToQuestionDtoWithoutType(Question question);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "questionType", source = "questionTypeDTO")
    @Mapping(target = "deleted",source = "deleted")
    Question questionDtoForCreateOrUpdateToQuestion(QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate);
    default List<QuestionDTOWithoutAnswersAndType> mapQuestionListToQuestionDtoWithoutAnswersAndTypeList(List<Question> questionList){
        return questionList.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionToQuestionDtoWithoutAnswersAndType)
                .collect(Collectors.toList());
    }

    default List<QuestionDTO> mapQuestionListToQuestionDtoList(List<Question> questionList){
        return questionList.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionToQuestionDto)
                .collect(Collectors.toList());
    }
    default List<QuestionDTOWithoutAnswer> mapQuestionListToQuestionDtoWithoutAnswerList(List<Question> questionList){
        return questionList.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionToQuestionDtoWithoutAnswer)
                .collect(Collectors.toList());
    }
    default List<QuestionDTOWithoutQuestionType> mapQuestionListToQuestionDtoWithoutQuestionTypeList(List<Question> questionList){
        return questionList.stream()
                .filter(Objects::nonNull)
                .map(INSTANCE::questionToQuestionDtoWithoutType)
                .collect(Collectors.toList());
    }
}
