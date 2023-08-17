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
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnswerDTOForCreateOrUpdate {
    @NotNull(groups = UpdateValidationGroup.class,message = "id must be not null")
    @Positive(groups = UpdateValidationGroup.class, message = "id must be more than zero")
    private BigInteger id;
    @NotNull(message = "answer body must be not empty")
    @Size(max = 3000,message = "answer body max size 3000")
    @NotEmpty(message = "answer body must be not empty")
    private String answerBody;
    private boolean answerCorrect;

    private boolean deleted;
    @NotNull(message = "question must be not empty")
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
