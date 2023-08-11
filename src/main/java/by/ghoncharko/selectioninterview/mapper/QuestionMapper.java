package by.ghoncharko.selectioninterview.mapper;

import by.ghoncharko.selectioninterview.dto.QuestionDTO;
import by.ghoncharko.selectioninterview.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "questionType", source = "questionTypeDTO")
    @Mapping(target = "answers", source = "answers")
    Question dtoToEntity(QuestionDTO questionDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "questionBody", source = "questionBody")
    @Mapping(target = "questionTypeDTO", source = "questionType")
    @Mapping(target = "answers", source = "answers")
    QuestionDTO entityToDto(Question question);
}
