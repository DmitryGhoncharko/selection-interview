package by.ghoncharko.selectioninterview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnswerDTOForCreateOrUpdate {
    private BigInteger id;
    private String answerBody;
    private boolean answerCorrect;

    private boolean deleted;

    private QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerDTOForCreateOrUpdate answerDTO = (AnswerDTOForCreateOrUpdate) o;

        return Objects.equals(id, answerDTO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
