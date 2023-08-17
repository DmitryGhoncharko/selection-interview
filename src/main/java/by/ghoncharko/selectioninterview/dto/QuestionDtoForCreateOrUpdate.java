package by.ghoncharko.selectioninterview.dto;

import by.ghoncharko.selectioninterview.validation.group.UpdateValidationGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotNull(groups = UpdateValidationGroup.class,message = "id must be not null")
    @Positive(groups = UpdateValidationGroup.class, message = "id must be more than zero")
    private BigInteger id;
    @NotEmpty(message = "question body must be not empty")
    @Size(max = 5000, message = "question body max size 5000")
    @NotEmpty(message = "question body must be not empty")
    private String questionBody;

    private boolean deleted;
    @NotNull(message = "question type must be not empty")
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
