package by.ghoncharko.selectioninterview.dto;

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
public class QuestionDtoForCreateOrUpdate {

    private BigInteger id;
    private String questionBody;

    private boolean deleted;

    private QuestionTypeDTOForCreateOrUpdate questionTypeDTO;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDtoForCreateOrUpdate that = (QuestionDtoForCreateOrUpdate) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
