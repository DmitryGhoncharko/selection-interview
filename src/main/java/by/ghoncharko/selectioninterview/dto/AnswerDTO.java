package by.ghoncharko.selectioninterview.dto;

import by.ghoncharko.selectioninterview.entity.Question;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnswerDTO {
    private BigInteger id;
    private String answerBody;
    private boolean answerCorrect;

    private Timestamp dateCreated;

    private Timestamp lastDateUpdated;
    private QuestionDTO questionDTO;

    private boolean deleted;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerDTO answerDTO = (AnswerDTO) o;

        return id != null ? id.equals(answerDTO.id) : answerDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
