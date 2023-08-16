package by.ghoncharko.selectioninterview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTOWithoutAnswer {
    private BigInteger id;
    private String questionBody;
    private boolean deleted;

    private Timestamp dateCreated;

    private Timestamp lastDateUpdated;
    private QuestionTypeDTOWithoutQuestions questionTypeDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDTOWithoutAnswer that = (QuestionDTOWithoutAnswer) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
