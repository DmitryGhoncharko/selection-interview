package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.AnswerDTO;
import by.ghoncharko.selectioninterview.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "question", source = "questionDTO")
    Answer dtoToEntity(AnswerDTO answerDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "answerBody", source = "answerBody")
    @Mapping(target = "answerCorrect", source = "answerCorrect")
    @Mapping(target = "questionDTO", source = "question")
    AnswerDTO entityToDto(Answer answer);
}
