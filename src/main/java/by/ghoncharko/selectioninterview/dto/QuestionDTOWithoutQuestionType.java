package by.ghoncharko.selectioninterview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTOWithoutQuestionType {
    private BigInteger id;
    private String questionBody;

    private boolean deleted;
    private Timestamp dateCreated;

    private Timestamp lastDateUpdated;
    private List<AnswerDTOWithoutQuestion> answers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDTOWithoutQuestionType that = (QuestionDTOWithoutQuestionType) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
