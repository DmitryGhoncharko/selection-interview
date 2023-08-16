package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.AnswerDTO;
import by.ghoncharko.selectioninterview.dto.AnswerDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.AnswerDTOWithoutQuestion;
import by.ghoncharko.selectioninterview.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "question", source = "questionDTO")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "dateCreated",source = "dateCreated")
    Answer answerDtoToAnswer(AnswerDTO answerDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "questionDTO", source = "question")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "dateCreated",source = "dateCreated")
    AnswerDTO answerToAnswerDto(Answer answer);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "dateCreated",source = "dateCreated")
    AnswerDTOWithoutQuestion answerToAnswerDtoWithoutQuestion(Answer answer);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "lastDateUpdated", source = "lastDateUpdated")
    @Mapping(target = "dateCreated",source = "dateCreated")
    Answer answerDtoWithoutQuestionToAnswer(AnswerDTOWithoutQuestion answerDTOWithoutQuestion);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "deleted",source = "deleted")
    @Mapping(target = "question",source = "questionDtoForCreateOrUpdate")
    Answer answerDtoForCreateOrUpdateToAnswer(AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate);


    default List<AnswerDTO> mapAnswerListToAnswerDtoList(List<Answer> answerList){
        if(answerList!=null){
            return answerList.stream()
                    .filter(Objects::nonNull)
                    .map(INSTANCE::answerToAnswerDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    default List<AnswerDTOWithoutQuestion> mapAnswerListToAnswerDtoWithoutQuestionList(List<Answer> answerList){
       if(answerList != null){
           return answerList.stream()
                   .filter(Objects::nonNull)
                   .map(INSTANCE::answerToAnswerDtoWithoutQuestion)
                   .collect(Collectors.toList());
       }
       return new ArrayList<>();
    }
}
